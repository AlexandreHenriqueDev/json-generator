package br.com.dev.jsongenerator.services.impl;

import br.com.dev.jsongenerator.dto.ObjectReaderDto;
import br.com.dev.jsongenerator.enums.TypeEnum;
import br.com.dev.jsongenerator.services.JsonGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static br.com.dev.jsongenerator.constants.ObjectsFormConstant.*;
import static br.com.dev.jsongenerator.utils.ObjectFormUtils.readObject;
import static br.com.dev.jsongenerator.utils.ObjectFormUtils.toListDto;

@Slf4j
@Service
public class JsonGeneratorServiceImpl implements JsonGeneratorService {

    private StringBuilder sb;

    @Override
    public ResponseEntity processGenericObject(List<ObjectReaderDto> listObjectReader) {
        sb = new StringBuilder();

        return ResponseEntity.ok().body(readProperties(listObjectReader, false));
    }

    protected String readProperties(List<ObjectReaderDto> listObjectReader, boolean isArray) {
        if(!isArray) {
            sb.append(OPEN_BRACES);
        }
        Integer size = listObjectReader.size();
        for (var i = 0; i < size; i++) {
            ObjectReaderDto objectReader = listObjectReader.get(i);
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
                    sb.append(readObject(objectReader.getProperty(), (Long) objectReader.getValue()));
                    break;
                case BOOLEAN:
                    sb.append(readObject(objectReader.getProperty(), (Boolean) objectReader.getValue()));
                    break;
                case OBJECT:
                    if(!isArray) {
                        sb.append(MARKS).append(objectReader.getProperty()).append(MARKS).append(TWO_DOTS);
                    }
                    readProperties(toListDto((List<Object>) objectReader.getValue()), false);
                    break;
                case ARRAY:
                    sb.append(MARKS).append(objectReader.getProperty()).append(MARKS).append(TWO_DOTS);
                    mountObjectArray(objectReader.getValue(), typeObject, objectReader.getSize());
                    break;
                default:
                    log.error("Tipo inválido!");
                    throw new EnumConstantNotPresentException(TypeEnum.class, "Tipo inválido");
            }
            if(!isArray) {
                sb.append(size.equals(i + 1) ? BREAK_LINE : COMMA + BREAK_LINE);
            }
        }

        sb.append(isArray ? COMMA : CLOSE_BRACES);
        return sb.toString();
    }

    private void mountObjectArray(Object object, TypeEnum type, Integer size) {
        sb.append(OPEN_BRACKETS);
        for(var i = 0; i< size; i++) {
            readProperties(toListDto((Arrays.asList(object))), true);
        }
        sb.append(CLOSE_BRACKETS);
    }

}
