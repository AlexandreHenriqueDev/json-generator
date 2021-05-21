package br.com.dev.jsongenerator.controllers;

import br.com.dev.jsongenerator.dto.ObjectReaderDto;
import br.com.dev.jsongenerator.services.JsonGeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@Api(value = "JsonGenerator endpoint", tags = {"JsonGenerator endpoint"})
@RestController
@RequestMapping("/api/v1")
public class JsonGeneratorController {

    @Autowired
    private JsonGeneratorService service;

    @PostMapping(value = "/getObject")
    @ApiOperation(httpMethod = "POST",
            value = "Retorna um novo Json com valores randomicos ou estáticos de acordo com o objeto passado",
            response = ResponseEntity.class,
            nickname="getObject")
    public ResponseEntity<ResponseBody> getObject(@RequestBody List<ObjectReaderDto> listObjectReader) throws ParseException {
        return service.processGenericObject(listObjectReader);
    }

}
