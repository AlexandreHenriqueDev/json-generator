package br.com.dev.jsongenerator.services.impl;

import br.com.dev.jsongenerator.dto.ObjectReaderDto;
import br.com.dev.jsongenerator.enums.FormatterEnum;
import br.com.dev.jsongenerator.enums.TypeEnum;
import br.com.dev.jsongenerator.exceptions.ErrorArgumentException;
import br.com.dev.jsongenerator.exceptions.ErrorEnumException;
import br.com.dev.jsongenerator.exceptions.IntegrationException;
import br.com.dev.jsongenerator.services.GeradorNomesServiceClient;
import br.com.dev.jsongenerator.services.JsonGeneratorService;
import br.com.dev.jsongenerator.services.LoremIpsumServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.com.dev.jsongenerator.constants.ObjectsFormConstant.*;
import static br.com.dev.jsongenerator.utils.ObjectFormUtils.readObject;
import static br.com.dev.jsongenerator.utils.ObjectFormUtils.toListDto;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class JsonGeneratorServiceImpl implements JsonGeneratorService {

    private StringBuilder sb;

    @Autowired
    LoremIpsumServiceClient loremIpsumServiceClient;

    @Autowired
    GeradorNomesServiceClient geradorNomesServiceClient;

    @Override
    public ResponseEntity processGenericObject(List<ObjectReaderDto> listObjectReader) throws Exception {
        sb = new StringBuilder();

        return ResponseEntity.ok().body(readProperties(listObjectReader, false));
    }

    protected String readProperties(List<ObjectReaderDto> listObjectReader, Boolean isArray) throws Exception {
        if(Boolean.FALSE.equals(isArray)) {
            sb.append(OPEN_BRACES);
        }
        Integer size = listObjectReader.size();
        for (var i = 0; i < size; i++) {
            ObjectReaderDto objectReader = listObjectReader.get(i);
            if(Boolean.TRUE.equals(nonNull(objectReader.getIsNull())) && Boolean.TRUE.equals(objectReader.getIsNull())) {
                sb.append(MARKS).append(objectReader.getProperty()).append(MARKS).append(TWO_DOTS).append("null");
            } else {
                TypeEnum typeObject = objectReader.getType();
                switch (typeObject) {
                    case STRING:
                        sb.append(readObject(objectReader.getProperty(), (String) objectReader.getValue(), objectReader.getSize(), objectReader.getFormatter()));
                        break;
                    case INTEGER:
                        sb.append(readObject(objectReader.getProperty(), (Integer) objectReader.getValue(), objectReader.getSize()));
                        break;
                    case DOUBLE:
                        sb.append(readObject(objectReader.getProperty(), (Double) objectReader.getValue()));
                        break;
                    case LONG:
                        sb.append(readObject(objectReader.getProperty(), (Long) objectReader.getValue(), objectReader.getFormatter()));
                        break;
                    case BOOLEAN:
                        sb.append(readObject(objectReader.getProperty(), (Boolean) objectReader.getValue()));
                        break;
                    case DATE:
                        sb.append(readObject(objectReader.getProperty(), (Date) objectReader.getValue(), objectReader.getFormatter()));
                        break;
                    case OBJECT:
                        if(Boolean.FALSE.equals(isArray)) {
                            sb.append(MARKS).append(objectReader.getProperty()).append(MARKS).append(TWO_DOTS);
                        }
                        readProperties(toListDto((List<Object>) objectReader.getValue()), false);
                        break;
                    case ARRAY:
                        sb.append(MARKS).append(objectReader.getProperty()).append(MARKS).append(TWO_DOTS);
                        try {
                            mountObjectArray(objectReader.getValue(), objectReader.getSize());
                        } catch (IntegrationException e) {
                            e.printStackTrace();
                            throw e;
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new ErrorArgumentException("Erro ao montar o objeto: " + objectReader, e.getCause());
                        }
                        break;
                    case CUSTOM_STRING:
                        generateCustomObject(objectReader.getProperty(), objectReader.getFormatter(), objectReader.getSize());
                        break;
                    default:
                        log.error("Tipo inválido!");
                        throw new ErrorEnumException(TypeEnum.class, "Tipo inválido");
                }
            }
            if(Boolean.TRUE.equals(isArray)) {
                sb.append(size.equals(i + 1) ? BREAK_LINE : COMMA + BREAK_LINE);
            }
        }

        sb.append(Boolean.TRUE.equals(isArray) ? COMMA : CLOSE_BRACES);
        return sb.toString();
    }

    private void mountObjectArray(Object object, Integer size) throws Exception {
        sb.append(OPEN_BRACKETS);
        for(var i = 0; i< size; i++) {
            readProperties(toListDto((Arrays.asList(object))), true);
        }
        sb.append(CLOSE_BRACKETS);
    }

    private void generateCustomObject(String property, FormatterEnum formatter, Integer size) throws IntegrationException {
        switch (formatter) {
            case LOREM_TEXT:
                String loremText = getLoremText(size);
                if(nonNull(loremText)) {
                    sb.append(MARKS)
                            .append(property)
                            .append(MARKS)
                            .append(TWO_DOTS)
                            .append(MARKS)
                            .append(loremText)
                            .append(MARKS);
                }
                break;
            case NAME_PT_BR:
                String namePtBr = getRandomNamePtBr();
                if(nonNull(namePtBr)) {
                    sb.append(MARKS)
                            .append(property)
                            .append(MARKS)
                            .append(TWO_DOTS)
                            .append(MARKS)
                            .append(namePtBr)
                            .append(MARKS);
                }
                break;
            default:
                break;
        }
    }

    private String getLoremText(Integer length) throws IntegrationException {
        try {
            return loremIpsumServiceClient.getRandomText(nonNull(length) ? length : 1);
        } catch (Exception e) {
            throw new IntegrationException("Erro na integração com a API LoremIpsum", e.getCause());
        }
    }

    private String getRandomNamePtBr() throws IntegrationException {
        try {
            return String.join(" ", geradorNomesServiceClient.getRandomText());
        } catch (Exception e) {
            throw new IntegrationException("Erro na integração com a API Gerador de Nomes", e.getCause());
        }
    }

}
