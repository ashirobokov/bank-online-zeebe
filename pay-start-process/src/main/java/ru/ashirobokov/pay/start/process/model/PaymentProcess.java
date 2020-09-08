package ru.ashirobokov.pay.start.process.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcess {
    private String pId;
    private Payment payment;
}
