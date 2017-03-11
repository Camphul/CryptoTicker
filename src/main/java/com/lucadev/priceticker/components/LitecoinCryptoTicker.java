package com.lucadev.priceticker.components;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Litecoin price ticker
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
@Component
public class LitecoinCryptoTicker implements CryptoTicker {

    @Autowired
    private List<Market> markets;

    @Autowired
    private Logger appLogger;

    /**
     * Market name -> index in list
     */
    private Map<String, Integer> marketMap = new HashMap<>();

    private long lastRefresh = 0;

    private double avgPrice = 0;

    public LitecoinCryptoTicker() {

    }

    @PostConstruct
    public void setup() {
        List<Market> legitSources = new ArrayList<>();
        for (Market market : markets) {
            if(market.getSupportedCryptoCurrency().equalsIgnoreCase("LTC")) {
                legitSources.add(market);
            }
        }
        markets = legitSources;
        for (int i = 0; i < markets.size(); i++) {
            marketMap.put(markets.get(i).getSourceName().toLowerCase(), i);
        }
    }

    public List<Market> getMarkets() {
        return markets;
    }

    @Override
    public void addMarket(Market market) {
        markets.add(market);
        marketMap.put(market.getSourceName().toLowerCase(), markets.size()-1);
    }

    @Override
    public Market getMarketByName(String market) {
        if(!marketMap.containsKey(market.toLowerCase())) {
            return null;
        }

        int index = marketMap.get(market.toLowerCase());
        if(getMarkets().size() < index) {
            return null;
        }

        return getMarkets().get(index);
    }

    @Override
    public String getCryptoAbbreviation() {
        return "LTC";
    }

    @Override
    public String getFullCryptoName() {
        return "Litecoin";
    }

    @Override
    public double getAveragePrice() {
        return avgPrice;
    }

    @Override
    public double refreshPrices() {
        double price = 0;
        for (Market source : markets) {
            price += source.refreshPrice();
        }
        avgPrice = price / markets.size();
        lastRefresh = System.currentTimeMillis();
        return avgPrice;
    }

    @Override
    public long getLastRefreshTime() {
        return lastRefresh;
    }
}
