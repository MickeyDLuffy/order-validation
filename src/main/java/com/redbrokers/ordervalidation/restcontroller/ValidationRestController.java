package com.redbrokers.ordervalidation.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.redbrokers.ordervalidation.dto.Order;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/validate")
public class ValidationRestController {

    @PostMapping("/order/{clientId}")
    public ResponseEntity<?> validateAndSendForProcessing(@RequestBody Order order,
                                                          @PathVariable String clientId) {

    }
}
