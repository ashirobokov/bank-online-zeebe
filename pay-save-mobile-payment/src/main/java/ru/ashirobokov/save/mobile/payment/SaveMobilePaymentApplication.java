package ru.ashirobokov.save.mobile.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SaveMobilePaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaveMobilePaymentApplication.class, args);
        log.info("SaveMobilePaymentApplication started");
    }
}
