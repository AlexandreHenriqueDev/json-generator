package br.com.dev.jsongenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JsonGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonGeneratorApplication.class, args);
	}

}
