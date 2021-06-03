package br.gov.sp.fatec.projetolabiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProjetoLabIvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoLabIvApplication.class, args);
    }
}
