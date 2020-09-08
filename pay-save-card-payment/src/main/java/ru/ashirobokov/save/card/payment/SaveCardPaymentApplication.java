package ru.ashirobokov.save.card.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SaveCardPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaveCardPaymentApplication.class, args);
        log.info("SaveCardPaymentApplication started");
    }
}
