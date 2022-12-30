package com.schambeck.erp.sales.app.entrypoint.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitConfig {
    @Value("${order.queue}")
    String orderQueueName;
    @Value("${order-exchange}")
    String orderExchangeName;
    @Value("${order-routing-key}")
    String orderRoutingKey;

    @Value("${dead-letter.queue}")
    String deadLetterQueueName;
    @Value("${dead-letter-exchange}")
    String deadLetterExchangeName;
    @Value("${dead-letter-routing-key}")
    String deadLetterRoutingKey;

    @Value("${x-dead-letter-exchange}")
    String xDeadLetterExchangeName;
    @Value("${x-dead-letter-routing-key}")
    String xDeadLetterRoutingKey;

    @Bean
    RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Queue orderQueue() {
        return QueueBuilder.durable(orderQueueName)
                .withArgument(xDeadLetterExchangeName, deadLetterExchangeName)
                .withArgument(xDeadLetterRoutingKey, deadLetterRoutingKey)
                .build();
    }

    @Bean
    TopicExchange orderExchange() {
        return new TopicExchange(orderExchangeName);
    }

    @Bean
    Binding orderBinding(Queue orderQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(orderRoutingKey);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(deadLetterQueueName).build();
    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(deadLetterExchangeName);
    }

    @Bean
    Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(deadLetterRoutingKey);
    }
}
