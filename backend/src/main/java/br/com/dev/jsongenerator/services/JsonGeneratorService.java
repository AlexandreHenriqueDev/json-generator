package br.com.dev.jsongenerator.services;

import br.com.dev.jsongenerator.dto.ObjectReaderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JsonGeneratorService {

    ResponseEntity processGenericObject(List<ObjectReaderDto> listObjectReader) throws Exception;

}
