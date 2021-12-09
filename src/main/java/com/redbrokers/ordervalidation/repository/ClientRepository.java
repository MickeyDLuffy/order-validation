package com.redbrokers.ordervalidation.repository;

import com.redbrokers.ordervalidation.dto.TickerQuantity;
import com.redbrokers.ordervalidation.entity.Client;
import com.redbrokers.ordervalidation.enums.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

      @Query(value = "SELECT b.balance FROM {h-schema}client c JOIN {h-schema}balance b ON b.id = c.balance_id " +
              "WHERE c.id = :clientID ", nativeQuery = true)
      double getBalanceForClient(UUID clientID);

      @Query(value = "SELECT a.ticker, b.asset_quantity FROM {h-schema}asset_portfolio b JOIN {h-schema}asset a ON a.id = b.asset_id " +
              "WHERE b.portfolio_id = :portfolioId", nativeQuery = true)
      List<Map<Ticker, Integer>> getProductsInPortfolio(UUID portfolioId);

}
