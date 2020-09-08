package ru.ashirobokov.pay.user.init.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ashirobokov.pay.user.init.model.Payment;
import ru.ashirobokov.pay.user.init.model.PaymentProcess;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
public class PayUserInitController {

    @Autowired
    private ZeebeClient zeebe;

    @RequestMapping(path = "/api/v1/payment", method = POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String startPayment(@RequestBody Payment payment) throws JsonProcessingException {
        log.info("Заявка на открытие счета {}", payment.toString());
        ObjectMapper mapper = new ObjectMapper();

        PaymentProcess process = new PaymentProcess(payment);
        String processLoad = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(process);
        log.info("ProcessLoad in json {}", processLoad);
        try {
            zeebe.newCreateInstanceCommand() //
                    .bpmnProcessId("bank-online-dev") //
                    .latestVersion() //
                    .variables(processLoad) //
                    .send().join();
        } catch (Exception e) {
            log.error("Could not tranform and send message due to: {}", e.getMessage());
        }

        return process.getPId();
    }
}
