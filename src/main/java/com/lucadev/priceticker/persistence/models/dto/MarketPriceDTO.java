package com.lucadev.priceticker.persistence.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

/**
 * DTO showed to the end user.
 * @author Luca
 * @since 3/10/2017
 */
public class MarketPriceDTO extends PriceDTO{

    private Map<String, Double> prices;

    public MarketPriceDTO(long timestamp, Map<String, Double> prices, double price, String currency) {
        super(timestamp, price, currency);
        this.prices = prices;
    }

    public MarketPriceDTO(boolean success, String message) {
        super(success, message);
    }

    @JsonIgnore
    public String[] getMarkets() {
        return prices.keySet().toArray(new String[prices.keySet().size()]);
    }

    public Map<String, Double> getPrices() {
        return prices;
    }
}
