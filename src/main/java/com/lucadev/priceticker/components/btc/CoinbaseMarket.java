package com.lucadev.priceticker.components.btc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucadev.priceticker.components.Market;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
@Component
public class CoinbaseMarket implements Market {

    private static final String apiUrl = "https://api.coinbase.com/v2/prices/spot?currency=%s";
    private static final String currency = "USD";

    private long lastUpdated = 0;
    private double recentPrice = 0;
    @Autowired
    private Logger appLogger;

    @Override
    public String getSupportedCryptoCurrency() {
        return "BTC";
    }

    @Override
    public double getRecentPrice() {
        return recentPrice;
    }

    @Override
    public double refreshPrice() {
        appLogger.info("Refreshing Coinbase prices");
        String priceURL = String.format(apiUrl, currency);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readValue(new URL(priceURL), JsonNode.class);
            JsonNode amountNode = node.get("data").get("amount");
            lastUpdated = System.currentTimeMillis();
            recentPrice = Double.parseDouble(amountNode.textValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recentPrice;
    }

    @Override
    public long getTimeLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String getSourceName() {
        return "Coinbase";
    }
}
