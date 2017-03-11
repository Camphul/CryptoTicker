package com.lucadev.priceticker.persistence.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

/**
 * DTO of market prices which gets json serialized for the REST api endpoints.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
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
