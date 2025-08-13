package com.favourcode.customer.producer;


import com.favourcode.customer.dto.FraudCheckPayload;
import com.favourcode.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerProducerService {
    private final RabbitTemplate rabbitTemplate;

    public void publishUserCreatedEvent(Customer user){
       FraudCheckPayload payload = new FraudCheckPayload( user.getFirstname(), user.getEmail(), user.getPublicId());
       rabbitTemplate.convertAndSend("user_exchange", "user.created", payload);
       log.info("Event sent to fraud check service");
    }

    public void publishUserBlockedNotification(String email){
      log.info("Sent notification to user with mail: {}", email);
    }
}
