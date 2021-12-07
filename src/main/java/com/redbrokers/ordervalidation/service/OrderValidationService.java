package com.redbrokers.ordervalidation.service;

import com.redbrokers.ordervalidation.dto.Order;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface OrderValidationService {
   ResponseEntity<?> validateAndSendForProcessing(Order order, UUID clientId);
}
