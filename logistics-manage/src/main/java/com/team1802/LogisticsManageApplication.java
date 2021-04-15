package com.team1802;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.team1802"})
public class LogisticsManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsManageApplication.class, args);
    }

}
