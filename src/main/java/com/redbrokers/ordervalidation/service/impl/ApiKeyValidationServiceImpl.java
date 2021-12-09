package com.redbrokers.ordervalidation.service.impl;

import com.redbrokers.ordervalidation.dto.ErrorMessage;
import com.redbrokers.ordervalidation.service.ApiKeyValidationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ApiKeyValidationServiceImpl implements ApiKeyValidationService {
    @Value("${order-validation.api_key}")
    private UUID apiKey;
    @Override
    public boolean isValid(UUID API_KEY) {
        return apiKey.equals(API_KEY);
    }
    @Override
    public ResponseEntity<ErrorMessage> onKeyInvalid() {

            var errorMessage = "Invalid API_KEY. Key is either poorly formed or incorrect";
            return new ResponseEntity<>(ErrorMessage
                    .builder()
                    .message(errorMessage)
                    .httpStatus(HttpStatus.FORBIDDEN).build(), HttpStatus.FORBIDDEN);

    }
}
