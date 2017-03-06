package com.lucadev.priceticker.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Recent price of a single market
 * @author Luca
 * @since 3/6/2017
 */
@Entity(name = "average_price")
public class AveragePrice {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @Column(name = "crypto_abbreviation", nullable = false)
    private String currency;

    @Column(name = "amount", nullable = false)
    private double amount;

    public AveragePrice() {
    }

    public AveragePrice(long timestamp, String currency, double amount) {
        this.timestamp = timestamp;
        this.currency = currency;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
}
