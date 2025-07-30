package org.example.metadata;

import org.springframework.boot.SpringApplication;

public class TestMetadataApplication {

    public static void main(String[] args) {
        SpringApplication.from(MetadataApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
