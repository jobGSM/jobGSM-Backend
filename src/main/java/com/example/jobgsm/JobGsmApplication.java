package com.example.jobgsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

// setting
// develop
// 김경수
// 최장우 테스트테스트테스트
@EnableJpaAuditing
@SpringBootApplication
@EnableCaching
@ConfigurationPropertiesScan
public class JobGsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobGsmApplication.class, args);
    }

}