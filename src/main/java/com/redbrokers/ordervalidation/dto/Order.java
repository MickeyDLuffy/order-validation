package com.redbrokers.ordervalidation.dto;

import com.redbrokers.ordervalidation.enums.Side;
import com.redbrokers.ordervalidation.enums.Ticker;
import lombok.Data;

@Data
public class Order {
    private int quantity;
    private Double price;
    private Side side;
    private Ticker product;


    @Override
    public String toString() {
        return "Order{" +
                "quantity=" + quantity +
                ", price=" + price +
                ", side=" + side +
                ", product=" + product +
                '}';
    }
}
