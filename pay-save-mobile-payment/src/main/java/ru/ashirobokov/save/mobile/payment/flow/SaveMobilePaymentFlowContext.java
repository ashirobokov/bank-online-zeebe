package ru.ashirobokov.save.mobile.payment.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.ashirobokov.save.mobile.payment.model.Payment;

@Slf4j
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class SaveMobilePaymentFlowContext {
    private String pId;
    private Payment payment;

    public static SaveMobilePaymentFlowContext fromJson(String json) {
        try {
            log.debug("[SaveMobilePaymentFlowContext.fromJson] INPUT Job variables details = {}", json);
            return new ObjectMapper().readValue(json, SaveMobilePaymentFlowContext.class);
        } catch (Exception e) {
            log.error("Could not deserialize context from JSON: {}", e.getMessage());
        }
        return null;
    }

    public String asJson() {
        try {
            log.debug("[SaveMobilePaymentFlowContext.asJson] OUTPUT Context details = {}", this.toString());
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Could not serialize context to JSON: {}", e.getMessage());
        }
        return null;
    }

}
