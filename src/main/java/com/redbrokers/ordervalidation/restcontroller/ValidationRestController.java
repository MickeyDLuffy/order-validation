package com.redbrokers.ordervalidation.restcontroller;

import com.redbrokers.ordervalidation.config.RabbitMQConfig;
import com.redbrokers.ordervalidation.service.OrderValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.redbrokers.ordervalidation.dto.Order;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/validate/{API_KEY}")
public class ValidationRestController {
    @Autowired
    RabbitTemplate template;

    @Autowired
    TopicExchange topicExchange;

    @Autowired
    RabbitMQConfig rabbitMQConfig;


    private final OrderValidationService validationService;
    @PostMapping("/orders/{clientId}")
    public ResponseEntity<?> validateAndSendForProcessing(@RequestBody Order order,
                                                          @RequestParam UUID portfolioId,
                                                          @PathVariable UUID API_KEY,
                                                          @PathVariable UUID clientId) {
       return validationService.validateAndSendForProcessing(order, clientId, portfolioId, API_KEY);
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody String message, @PathVariable UUID API_KEY){
        template.convertAndSend(topicExchange.getName(), RabbitMQConfig.routeKey, message);
        return ResponseEntity.ok("PUBLISHED");
    }

}

