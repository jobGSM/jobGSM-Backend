package com.example.jobgsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

// setting
// develop
@EnableJpaAuditing
@SpringBootApplication
public class JobGsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobGsmApplication.class, args);
    }

}
