package com.lucadev.priceticker.components.coinbase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucadev.priceticker.components.PriceSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * @author Luca
 * @since 3/6/2017
 */
@Component
public class CoinbasePriceSource implements PriceSource {

    private static final String apiUrl = "https://api.coinbase.com/v2/prices/spot?currency=%s";
    private static final String currency = "USD";

    private long lastUpdated = 0;
    private double recentPrice = 0;
    @Autowired
    private Logger logger;

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
        logger.info("Refreshing coinbase prices");
        String priceURL = String.format(apiUrl, currency);
        try {
            //com.fasterxml.jackson.core.JsonParser parser = jsonFactory.createParser(response);
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
