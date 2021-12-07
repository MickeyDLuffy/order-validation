package com.redbrokers.ordervalidation.service.impl;

import com.redbrokers.ordervalidation.dto.Order;
import com.redbrokers.ordervalidation.service.OrderValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class OrderValidationServiceImpl implements OrderValidationService {

    @Override
    public ResponseEntity<?> validateAndSendForProcessing(Order order, UUID clientId) {
        return null;
    }
}
