package com.redbrokers.ordervalidation.restcontroller;

import com.redbrokers.ordervalidation.service.OrderValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.redbrokers.ordervalidation.dto.Order;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/validate")
public class ValidationRestController {
    private OrderValidationService validationService;
    @PostMapping("/{API_KEY}/orders/{clientId}")
    public ResponseEntity<?> validateAndSendForProcessing(@RequestBody Order order,
                                                          @PathVariable String API_KEY,
                                                          @PathVariable String clientId) {
       return null;
    }
}
