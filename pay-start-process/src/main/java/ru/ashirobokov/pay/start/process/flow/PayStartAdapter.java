package ru.ashirobokov.pay.start.process.flow;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.client.api.worker.JobHandler;
import io.zeebe.client.api.worker.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ashirobokov.pay.start.process.model.Payment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;

@Slf4j
@Component
public class PayStartAdapter implements JobHandler  {

    @Autowired
    private ZeebeClient zeebe;

    private JobWorker subscription;

    @PostConstruct
    public void subscribe() {
        subscription = zeebe.newWorker()
                .jobType("pay-start")
                .handler(this)
                .timeout(Duration.ofMinutes(1))
                .open();
    }

    @PreDestroy
    public void closeSubscription() {
        subscription.close();
    }

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
            PayStartFlowContext context = PayStartFlowContext.fromJson(job.getVariables());
            log.debug("[PayStartAdapter.handle] Job full details = {}, context = {}", job.toJson(), context.toString());
            if (null != context) {
                log.info("..[PayStartAdapter.handle] Process Id = {}", context.getPId());
                Payment payment = context.getPayment();
                log.info("....Payment = {}", payment);

                client.newCompleteCommand(job.getKey()) //
                        .variables(context.asJson()) //
                        .send().join();
            }
            else {
                log.info("..[PayStartAdapter.handle] ERROR workflow context is null");
            }
        }

}
