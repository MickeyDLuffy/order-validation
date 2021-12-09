package com.redbrokers.ordervalidation.service;

import com.redbrokers.ordervalidation.dto.ErrorMessage;
import org.springframework.http.ResponseEntity;

import java.util.UUID;
public interface ApiKeyValidationService {
    ResponseEntity<ErrorMessage> onKeyInvalid();
    boolean isValid(UUID API_KEY);
}
