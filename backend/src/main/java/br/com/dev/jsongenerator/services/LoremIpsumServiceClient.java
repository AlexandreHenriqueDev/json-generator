package br.com.dev.jsongenerator.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Async
@FeignClient(name = "lorem-ipsum-client", url = "${lorem-ipsum.api.url}")
public interface LoremIpsumServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{numLines}${lorem-ipsum.api.plain-text}", produces = MediaType.TEXT_PLAIN_VALUE)
    String getRandomText(@PathVariable("numLines") Integer numLines);

}
