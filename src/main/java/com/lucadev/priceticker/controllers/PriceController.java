package com.lucadev.priceticker.controllers;

import com.lucadev.priceticker.components.CryptoTicker;
import com.lucadev.priceticker.components.PriceSource;
import com.lucadev.priceticker.persistence.models.dto.PriceDTO;
import com.lucadev.priceticker.persistence.models.dto.MarketPriceDTO;
import com.lucadev.priceticker.services.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Luca
 * @since 3/6/2017
 */
@RestController
public class PriceController extends BaseController {

    @Autowired
    private TickerService tickerService;

    @GetMapping("/currencies")
    public Set<String> getTickers() {
        return tickerService.getTickers().keySet();
    }

    @GetMapping("/{currency}/average")
    public PriceDTO getAveragePrice(@PathVariable String currency) {
        CryptoTicker ticker = tickerService.getTicker(currency.toUpperCase());
        if(ticker == null) {
            return new PriceDTO(false, "Not a valid currency given!");
        }
        return new PriceDTO(ticker.getLastRefreshTime(), ticker.getAveragePrice(), ticker.getCryptoAbbreviation());
    }

    @GetMapping("/{currency}/markets")
    public List<String> listAvailableMarket(@PathVariable String currency) {
        CryptoTicker ticker = tickerService.getTicker(currency.toUpperCase());
        if(ticker == null) {
            return Collections.emptyList();
        }
        List<String> markets = new ArrayList<>();
        ticker.getPriceSources().forEach(market -> markets.add(market.getSourceName()));
        return markets;
    }

    @GetMapping("/{currency}/all/price")
    public MarketPriceDTO getAllRecentMarketPrice(@PathVariable String currency) {
        CryptoTicker ticker = tickerService.getTicker(currency.toUpperCase());
        if(ticker == null) {
            return new MarketPriceDTO(false, "Not a valid currency given!");
        }
        double average = 0;
        long lastRefresh = 0;
        Map<String, Double> prices = new HashMap<>();
        for (PriceSource source : ticker.getPriceSources()) {
            prices.put(source.getSourceName(), source.getRecentPrice());
            average += source.getRecentPrice();
            if(lastRefresh < source.getTimeLastUpdated()) {
                lastRefresh = source.getTimeLastUpdated();
            }
        }
        average /= ticker.getPriceSources().size();
        return new MarketPriceDTO(lastRefresh, prices, average, ticker.getCryptoAbbreviation());
    }

    @GetMapping("/{currency}/{markets}/price")
    public MarketPriceDTO getRecentMarketPrice(@PathVariable String currency, @PathVariable String markets) {
        CryptoTicker ticker = tickerService.getTicker(currency.toUpperCase());
        if(ticker == null) {
            return new MarketPriceDTO(false, "Not a valid currency given!");
        }
        String marketNames[] = markets.split(",");
        double average = 0;
        long lastRefresh = 0;
        Map<String, Double> prices = new HashMap<>();
        for (String marketName : marketNames) {
            PriceSource source = ticker.getSourceByName(marketName);
            if(source == null) {
                return new MarketPriceDTO(false, marketName  + " is not a valid market!");
            }
            prices.put(source.getSourceName(), source.getRecentPrice());
            average += source.getRecentPrice();
            if(lastRefresh < source.getTimeLastUpdated()) {
                lastRefresh = source.getTimeLastUpdated();
            }
        }
        average /= marketNames.length;
        return new MarketPriceDTO(lastRefresh, prices, average, ticker.getCryptoAbbreviation());
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
