package com.redbrokers.ordervalidation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.redbrokers.ordervalidation.enums.Ticker;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = TickerQuantity.TickerQuantityBuilder.class)
public class TickerQuantity {
    private Ticker ticker;
    private double quantity;
    @JsonProperty("asset_quantity")
    private double tickerQuantity;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TickerQuantityBuilder {}
}
