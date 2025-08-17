package com.favourcode.order.consumer;

import com.favourcode.order.dto.OrderPlacementPayload;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerService {

    @RabbitListener(queues = "order.placed.queue")
    public void handleOrderPlacedQueue(OrderPlacementPayload payload){
        System.out.println(payload);
    }
}
