package com.lucadev.priceticker.components;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstracted price ticker with some methods implemented.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
public abstract class AbstractPriceTicker implements PriceTicker {

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

    public AbstractPriceTicker() {

    }

    @PostConstruct
    public void setup() {
        List<Market> legitSources = new ArrayList<>();
        for (Market market : markets) {
            if(market.getSupportedCryptoCurrency().equalsIgnoreCase(getCryptoAbbreviation())) {
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
