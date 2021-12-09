package com.redbrokers.ordervalidation.restcontroller;

import com.redbrokers.ordervalidation.service.OrderValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.redbrokers.ordervalidation.dto.Order;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/validate/{API_KEY}")
public class ValidationRestController {
    private final OrderValidationService validationService;
    @PostMapping("/orders/{clientId}")
    public ResponseEntity<?> validateAndSendForProcessing(@RequestBody Order order,
                                                          @RequestParam UUID portfolioId,
                                                          @PathVariable UUID API_KEY,
                                                          @PathVariable UUID clientId) {
       return validationService.validateAndSendForProcessing(order, clientId, portfolioId, API_KEY);
    }
}
