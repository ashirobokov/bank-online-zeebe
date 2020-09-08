package ru.ashirobokov.save.account.payment.flow;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.client.api.worker.JobHandler;
import io.zeebe.client.api.worker.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ashirobokov.save.account.payment.model.Payment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;

@Slf4j
@Component
public class SaveAccountPaymentAdapter implements JobHandler {

    @Autowired
    private ZeebeClient zeebe;

    private JobWorker subscription;

    @PostConstruct
    public void subscribe() {
        subscription = zeebe.newWorker()
                .jobType("pay-save-2-account")
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
        SaveAccountPaymentFlowContext context = SaveAccountPaymentFlowContext.fromJson(job.getVariables());
        log.debug(".[SaveAccountPaymentAdapter.handle] Job full details = {}, context = {}", job.toJson(), context.toString());
        if (null != context) {
            log.info("..[SaveAccountPaymentAdapter.handle] Process Id = {}", context.getPId());

            /*
             *
             *          TODO выполнить проверку, используя вызовы Веб сервисов СПАРК
             *          Отладочная проверка закончена : формируем признак успешного завершения
             *          application.setAppCheck(true), by default is "false"
             *          отправляем результат дальше по workflow
             */

            Payment payment = context.getPayment();
            log.info("Payment = {}", payment);

            client.newCompleteCommand(job.getKey()) //
                    .variables(context.asJson()) //
                    .send().join();
        }
        else {
            log.info("..[SaveAccountPaymentAdapter.handle] ERROR workflow context is null");
        }

    }
}
