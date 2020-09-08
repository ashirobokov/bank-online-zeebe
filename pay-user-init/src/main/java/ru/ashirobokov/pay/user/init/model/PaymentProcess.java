package ru.ashirobokov.pay.user.init.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PaymentProcess {
    private String pId; // (UUID.randomUUID().toString())
    private Payment payment;

    public PaymentProcess(Payment payment) {
        this.pId = UUID.randomUUID().toString();
        this.payment = payment;
    }
}
