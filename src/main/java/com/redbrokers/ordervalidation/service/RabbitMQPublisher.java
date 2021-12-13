package com.redbrokers.ordervalidation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redbrokers.ordervalidation.config.RabbitMQConfig;
import com.redbrokers.ordervalidation.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class RabbitMQMessagePublisher {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TopicExchange topicExchange;

    void publish(Order order) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(topicExchange.getName(), RabbitMQConfig.routeKey, new ObjectMapper().writeValueAsString(order));
        log.info(String.valueOf(order));

    }

}
