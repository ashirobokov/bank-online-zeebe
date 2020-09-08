package ru.ashirobokov.pay.user.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PayUserInitApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayUserInitApplication.class, args);
        log.info("PayUserInitApplication started");
    }
}
