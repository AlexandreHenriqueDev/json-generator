package br.com.dev.jsongenerator.controllers;

import br.com.dev.jsongenerator.dto.ObjectReaderDto;
import br.com.dev.jsongenerator.exceptions.ErrorEnumException;
import br.com.dev.jsongenerator.services.JsonGeneratorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class JsonGeneratorController {

    @Autowired
    private JsonGeneratorService service;

    @RequestMapping(value = "/getObject",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST",
            value = "Retorna um novo Json com valores randomicos ou estáticos de acordo com o objeto passado",
            response = ResponseEntity.class,
            nickname="getObject")
    public ResponseEntity<ResponseBody> getObject(@RequestBody List<ObjectReaderDto> listObjectReader) throws Exception, ErrorEnumException {
        return service.processGenericObject(listObjectReader);
    }

}
