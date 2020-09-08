package ru.ashirobokov.pay.start.process;

import io.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class PayStartProcessApplication {

    @Autowired
    private ZeebeClient zeebeClient;

    @PostConstruct
    public void deployWorkflowToZeebe() {
        zeebeClient.newDeployCommand() //
                .addResourceFromClasspath("bank-online-dev.bpmn") //
                .send().join();
    }

    public static void main(String[] args) {
        SpringApplication.run(PayStartProcessApplication.class, args);
        log.info("PayStartApplication started");
    }

}
