package com.favourcode.fraud.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FraudConfig {
    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange("user_exchange");
    }

    @Bean
    public Queue userCreatedQueue() {
        return new Queue("fraud.user.created.queue");
    }

    @Bean
    public Binding bindUserCreated(Queue userCreatedQueue,
                                   TopicExchange userExchange) {
        return BindingBuilder.bind(userCreatedQueue).to(userExchange).with(
                "user.created");
    }

    @Bean
       public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
           return new Jackson2JsonMessageConverter();
       }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,Jackson2JsonMessageConverter converter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);
        return factory;
    }

    @Bean
     public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
         RabbitTemplate template = new RabbitTemplate(connectionFactory);
         template.setMessageConverter(jackson2JsonMessageConverter());
         return  template;
     }
}
