package com.favourcode.fraud.consumer;


import com.favourcode.fraud.dto.FraudCheckPayload;
import com.favourcode.fraud.service.FraudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FraudConsumer {

    private final FraudService fraudService;

    @RabbitListener(queues = "fraud.user.created.queue")
    public void handleUserCreatedEvent(FraudCheckPayload payload){
        log.info(" {}", payload );
        fraudService.isFraudulentCustomer(payload.publicId(), payload.email());

    }
}
