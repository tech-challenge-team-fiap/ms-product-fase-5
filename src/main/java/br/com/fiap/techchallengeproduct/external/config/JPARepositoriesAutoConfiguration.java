package br.com.fiap.techchallengeproduct.external.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("br.com.fiap.techchallengeproduct.external.infrastructure.repositories")
public class JPARepositoriesAutoConfiguration {

}
