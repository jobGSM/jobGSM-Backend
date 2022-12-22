package com.example.jobgsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class JobGsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobGsmApplication.class, args);
    }

}
