package org.example.ebankify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@SpringBootApplication


public class EBankifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankifyApplication.class, args);
    }

}
