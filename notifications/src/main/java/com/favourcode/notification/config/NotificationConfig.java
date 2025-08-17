package com.favourcode.notification.config;


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

@Configuration
public class NotificationConfig {

    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange("user_exchange");
    }


    @Bean
    public Queue userCreatedNotificationQueue() {
        return new Queue("notification.user.created.queue");
    }


    @Bean
    public Binding userCreatedNotificationBinding(Queue userCreatedNotificationQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(userCreatedNotificationQueue).to(userExchange).with("user.created");
    }

    @Bean
     public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
         return new Jackson2JsonMessageConverter();
     }

     @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
     }

     @Bean
     public SimpleRabbitListenerContainerFactory listenerContainerFactory(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter){
         SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
         factory.setConnectionFactory(connectionFactory);
         factory.setMessageConverter(messageConverter);
         return factory;
     }

}
