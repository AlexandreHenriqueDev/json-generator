package br.com.dev.jsongenerator.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Async
@FeignClient(name = "gerador-nomes-client", url = "${gerador-nome.api.url}")
public interface GeradorNomesServiceClient {

    @GetMapping(value = "/${gerador-nome.api.servico}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> getRandomText();

}
