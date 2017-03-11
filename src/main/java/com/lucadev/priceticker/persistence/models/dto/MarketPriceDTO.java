package com.lucadev.priceticker.persistence.models.dto;

/**
 * DTO showed to the end user.
 * @author Luca
 * @since 3/10/2017
 */
public class MarketPriceDTO extends PriceDTO{

    private String[] markets;

    public MarketPriceDTO(long timestamp, String[] markets,  double price) {
        super(timestamp, price);
        this.markets = markets;
    }

    public MarketPriceDTO(boolean success, String message) {
        super(success, message);
    }

    public String[] getMarkets() {
        return markets;
    }
}
