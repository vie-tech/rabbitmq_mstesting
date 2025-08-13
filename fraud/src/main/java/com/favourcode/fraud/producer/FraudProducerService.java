package com.favourcode.fraud.producer;


import com.favourcode.fraud.dto.FraudCheckResultPayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FraudProducerService {

    private final RabbitTemplate rabbitTemplate;

    public void publishUserFraudResult(boolean isFraudulent, String userPublicId, String email){
        FraudCheckResultPayload payload = new FraudCheckResultPayload(userPublicId, isFraudulent, email);
        rabbitTemplate.convertAndSend("user_exchange", "user.fraud.result", payload);
        log.info("Event sent to fraud result queue");
    }
}
