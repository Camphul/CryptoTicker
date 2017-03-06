package com.lucadev.priceticker.services;

import com.lucadev.priceticker.components.CryptoTicker;
import com.lucadev.priceticker.components.PriceSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luca
 * @since 3/6/2017
 */
@Service
public class TickerService {

    private Map<String, CryptoTicker> tickers = new HashMap<>();

    @Autowired
    private List<CryptoTicker> tickerList;

    @Autowired
    private Logger logger;

    @Autowired
    private PriceService priceService;

    @PostConstruct
    public void setup() {
        tickerList.forEach(ticker -> {
            tickers.put(ticker.getCryptoAbbreviation(), ticker);
            logger.info("Registered ticker: " + ticker.getCryptoAbbreviation());
        });
    }

    @Scheduled(fixedRate = 30000)
    public void refreshData() {
        logger.info("Refreshing ticker prices");
        for (CryptoTicker ticker : tickerList) {
            ticker.refreshPrices();
            priceService.addAveragePriceUpdate(ticker);
            for (PriceSource priceSource : ticker.getPriceSources()) {
                priceService.addMarketUpdate(priceSource);
            }
        }
    }

    public CryptoTicker getTicker(String cryptoAbbreviation) {
        return tickers.get(cryptoAbbreviation);
    }

    public Map<String, CryptoTicker> getTickers() {
        return tickers;
    }
}
