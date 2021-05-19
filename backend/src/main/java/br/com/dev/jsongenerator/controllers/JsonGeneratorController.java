package br.com.dev.jsongenerator.controllers;

import br.com.dev.jsongenerator.dto.ObjectReaderDto;
import br.com.dev.jsongenerator.services.JsonGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class JsonGeneratorController {

    @Autowired
    private JsonGeneratorService service;

    @PostMapping(value = "/getObject")
    public ResponseEntity<ResponseBody> getObject(@RequestBody List<ObjectReaderDto> listObjectReader) {
        return service.processGenericObject(listObjectReader);
    }

}
