package ru.ashirobokov.save.account.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SaveAccountPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaveAccountPaymentApplication.class, args);
            log.info("SaveAccountPaymentApplication started");
    }
}
