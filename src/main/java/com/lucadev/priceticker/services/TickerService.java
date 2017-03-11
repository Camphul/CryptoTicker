package com.lucadev.priceticker.services;

import com.lucadev.priceticker.components.CryptoTicker;
import com.lucadev.priceticker.components.Market;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for all the tickers.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
@Service
public class TickerService {

    /**
     * Map of all the tickers, key value is the crypto currency abbreviation in capitalized letters.
     */
    private Map<String, CryptoTicker> tickers = new HashMap<>();

    /**
     * Autowired list of all crypto tickers.
     */
    @Autowired
    private List<CryptoTicker> tickerList;

    /**
     * Slf4j2 logger
     */
    @Autowired
    private Logger appLogger;

    /**
     * Service that wraps repositories to gain price information.
     */
    @Autowired
    private PriceService priceService;

    /**
     * Once tickerList is autowired we'll sort it into the tickers map.
     */
    @PostConstruct
    public void setup() {
        tickerList.forEach(ticker -> {
            tickers.put(ticker.getCryptoAbbreviation(), ticker);
            appLogger.info("Registered ticker: " + ticker.getCryptoAbbreviation());
        });
    }

    /**
     * Refresh prices from all markets.
     * TODO: Make fixedRate a configuration value instead of hardcoded value.
     */
    @Scheduled(fixedRate = 30000)
    public void refreshData() {
        appLogger.info("Refreshing ticker prices");
        for (CryptoTicker ticker : tickerList) {
            ticker.refreshPrices();
            priceService.addAveragePriceUpdate(ticker);
            for (Market market : ticker.getMarkets()) {
                priceService.addMarketUpdate(market);
            }
        }
    }

    /**
     * Get a price ticker by its crypto abbreviation.
     * Case sensitive.
     * @param cryptoAbbreviation abbreviation of the crypto currency, most of the time in capital letters.
     * @return
     */
    public CryptoTicker getTicker(String cryptoAbbreviation) {
        return tickers.get(cryptoAbbreviation);
    }

    /**
     * Obtain the map of price tickers.
     * @return
     */
    public Map<String, CryptoTicker> getTickers() {
        return tickers;
    }
}
