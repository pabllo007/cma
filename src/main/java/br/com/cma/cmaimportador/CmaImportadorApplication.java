package br.com.cma.cmaimportador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.cma"})
@ConfigurationPropertiesScan
@EnableCaching
public class CmaImportadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmaImportadorApplication.class, args);
	}

}
