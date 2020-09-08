package ru.ashirobokov.save.account.payment.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.ashirobokov.save.account.payment.model.Payment;

@Slf4j
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class SaveAccountPaymentFlowContext {
    private String pId;
    private Payment payment;

    public static SaveAccountPaymentFlowContext fromJson(String json) {
        try {
            log.debug("[SaveCardPaymentFlowContext.fromJson] INPUT Job variables details = {}", json);
            return new ObjectMapper().readValue(json, SaveAccountPaymentFlowContext.class);
        } catch (Exception e) {
            log.error("Could not deserialize context from JSON: {}", e.getMessage());
        }
        return null;
    }

    public String asJson() {
        try {
            log.debug("[SaveCardPaymentFlowContext.asJson] OUTPUT Context details = {}", this.toString());
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Could not serialize context to JSON: {}", e.getMessage());
        }
        return null;
    }

}
