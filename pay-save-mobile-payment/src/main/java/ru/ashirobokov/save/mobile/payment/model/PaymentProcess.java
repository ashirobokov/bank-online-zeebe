package ru.ashirobokov.save.mobile.payment.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcess {
    private String pId;
    private Payment payment;
}
