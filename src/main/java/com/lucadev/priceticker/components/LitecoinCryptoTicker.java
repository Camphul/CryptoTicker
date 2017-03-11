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
 * @author Luca
 * @since 3/6/2017
 */
@Component
public class LitecoinCryptoTicker implements CryptoTicker {

    @Autowired
    private List<PriceSource> priceSources;

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
        List<PriceSource> legitSources = new ArrayList<>();
        for (PriceSource priceSource : priceSources) {
            if(priceSource.getSupportedCryptoCurrency().equalsIgnoreCase("LTC")) {
                legitSources.add(priceSource);
            }
        }
        priceSources = legitSources;
        for (int i = 0; i < priceSources.size(); i++) {
            marketMap.put(priceSources.get(i).getSourceName().toLowerCase(), i);
        }
    }

    @Override
    public List<PriceSource> getPriceSources() {
        return priceSources;
    }

    @Override
    public void addPriceSource(PriceSource priceSource) {
        priceSources.add(priceSource);
        marketMap.put(priceSource.getSourceName().toLowerCase(), priceSources.size()-1);
    }

    @Override
    public PriceSource getSourceByName(String market) {
        if(!marketMap.containsKey(market.toLowerCase())) {
            return null;
        }

        int index = marketMap.get(market.toLowerCase());
        if(getPriceSources().size() < index) {
            return null;
        }

        return getPriceSources().get(index);
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
        for (PriceSource source : priceSources) {
            price += source.refreshPrice();
        }
        avgPrice = price / priceSources.size();
        lastRefresh = System.currentTimeMillis();
        return avgPrice;
    }

    @Override
    public long getLastRefreshTime() {
        return lastRefresh;
    }
}