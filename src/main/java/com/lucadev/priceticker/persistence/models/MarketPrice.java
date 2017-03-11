package com.lucadev.priceticker.persistence.models;

import javax.persistence.*;

/**
 * Recent price of a single market
 * @author Luca
 * @since 3/6/2017
 */
@Entity(name = "market_price")
public class MarketPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    //Which market
    @Column(name = "market", nullable = false)
    private String market;

    @Column(name = "crypto_abbreviation", nullable = false)
    private String currency;

    @Column(name = "amount", nullable = false)
    private double amount;

    public MarketPrice() {
    }

    public MarketPrice(long timestamp, String market, String currency, double amount) {
        this.timestamp = timestamp;
        this.market = market;
        this.currency = currency;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMarket() {
        return market;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
}
