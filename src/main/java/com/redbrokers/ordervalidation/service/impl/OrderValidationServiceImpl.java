package com.redbrokers.ordervalidation.service.impl;

import com.redbrokers.ordervalidation.dto.ErrorMessage;
import com.redbrokers.ordervalidation.dto.Order;
import com.redbrokers.ordervalidation.enums.Side;
import com.redbrokers.ordervalidation.enums.Ticker;
import com.redbrokers.ordervalidation.exception.ClientNotFoundException;
import com.redbrokers.ordervalidation.exception.EntityNotFoundException;
import com.redbrokers.ordervalidation.repository.ClientRepository;
import com.redbrokers.ordervalidation.repository.PortfolioRepository;
import com.redbrokers.ordervalidation.service.ApiKeyValidationService;
import com.redbrokers.ordervalidation.service.OrderValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderValidationServiceImpl implements OrderValidationService {
    private final ClientRepository clientRepository;
    private final PortfolioRepository portfolioRepository;
    private final ApiKeyValidationService keyValidationService;
    @Override
    public ResponseEntity<?> validateAndSendForProcessing(Order order, UUID clientId,
                                                          UUID portfolioId,
                                                          UUID API_KEY) {
       String errorMessage = "";

       if(!keyValidationService.isValid(API_KEY)) {
           return this.keyValidationService.onKeyInvalid();
       }

        var client = clientRepository.findById(clientId)
               .orElseThrow(ClientNotFoundException::new);

//       var d = portfolioRepository.findByClientId(clientId);
       var portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Portfolio with id: " + portfolioId + "" +
                                " was not found"));

        /**
         * Check if the portfolio belongs to the client
         */
        if(!portfolio.getClientId().equals(clientId)) {
            errorMessage = "The client does not own this portfolio";
            return new ResponseEntity<>(ErrorMessage
                    .builder()
                    .message(errorMessage)
                    .httpStatus(HttpStatus.FORBIDDEN).build(), HttpStatus.FORBIDDEN);
        }


       if(order.getSide() == Side.BUY) {
         if(hasSufficientFunds(order, clientId)) {

         } else {
              errorMessage = "You do not have sufficient balance to complete this transaction";
             return new ResponseEntity<>(ErrorMessage
                     .builder()
                     .message(errorMessage)
                     .httpStatus(HttpStatus.PAYMENT_REQUIRED), HttpStatus.PAYMENT_REQUIRED);

         }
       } else if(order.getSide() == Side.SELL) {
           List<Ticker> userProducts = clientRepository.getProductsInPortfolio(portfolioId);
           if(true) {

           }else {
               errorMessage = "You do not have the stock you wish to sell";
               return new ResponseEntity<>(ErrorMessage
                       .builder()
                       .message(errorMessage)
                       .httpStatus(HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
           }
       }
       return null;
    }

    private boolean hasSufficientFunds(Order order, UUID clientId) {
        var balance = clientRepository.getBalanceForClient(clientId);
        double costPrice = order.getPrice() * order.getQuantity();
        return !(balance < costPrice);
    }

    private boolean hasStockToSell(Order order, UUID clientId) {
  return true;
    }
}
