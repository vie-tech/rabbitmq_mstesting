package com.favourcode.customer.config;


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
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

    @Bean

    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public TopicExchange userExchange(){
        return new TopicExchange("user_exchange");
    }

    @Bean
    public Queue fraudResultQueue(){
        return new Queue("customer.fraud.result.queue");
    }

    @Bean
    public Binding fraudResultBinding(Queue fraudResultQueue, TopicExchange userExchange){
        return BindingBuilder.bind(fraudResultQueue).to(userExchange).with("user.fraud.result");
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter());
        return  template;
    }

    @Bean
     public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
         SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
         factory.setConnectionFactory(connectionFactory);
         factory.setMessageConverter(converter);
         return factory;
     }
}
