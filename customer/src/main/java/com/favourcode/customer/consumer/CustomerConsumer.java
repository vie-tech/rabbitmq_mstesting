package com.favourcode.customer.consumer;


import com.favourcode.customer.dto.FraudCheckResultPayload;
import com.favourcode.customer.producer.CustomerProducerService;
import com.favourcode.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerConsumer {

    private final CustomerProducerService customerProducerService;
    private final CustomerService customerService;

    @RabbitListener(queues = "customer.fraud.result.queue")
    public void handleFraudCheckResult(FraudCheckResultPayload payload){

        if(payload.isFraudulent()){
            customerService.banFraudulentUser(payload.userId());
        }else {
            log.info("user with id {} is not fraudulent account verified", payload.userId());
        }
    }


}
