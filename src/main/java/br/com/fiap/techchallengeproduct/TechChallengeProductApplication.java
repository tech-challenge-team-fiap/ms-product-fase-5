package br.com.fiap.techchallengeproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.fiap.techchallengeproduct"})
@EntityScan(basePackages = "br.com.fiap.techchallengeproduct.external.infrastructure.entities")
public class TechChallengeProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechChallengeProductApplication.class, args);
	}

}
