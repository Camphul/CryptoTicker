package com.lucadev.priceticker.services;

import com.lucadev.priceticker.components.CryptoTicker;
import com.lucadev.priceticker.components.Market;
import com.lucadev.priceticker.persistence.models.AveragePrice;
import com.lucadev.priceticker.persistence.models.MarketPrice;
import com.lucadev.priceticker.persistence.repositories.AveragePriceRepository;
import com.lucadev.priceticker.persistence.repositories.MarketPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that makes it easier to handle individual markets and average markets.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
@Service
public class PriceService {

    /**
     * Individual markets
     */
    @Autowired
    private MarketPriceRepository marketPriceRepository;

    @Autowired
    private AveragePriceRepository averagePriceRepository;

    public void addMarketUpdate(MarketPrice price) {
        marketPriceRepository.save(price);
    }

    public void addMarketUpdate(Market source) {
        addMarketUpdate(new MarketPrice(source.getTimeLastUpdated(), source.getSourceName(), source.getSupportedCryptoCurrency(),
                source.getRecentPrice()));
    }

    public void addAveragePriceUpdate(AveragePrice averagePrice) {
        averagePriceRepository.save(averagePrice);
    }

    public void addAveragePriceUpdate(CryptoTicker ticker) {
        addAveragePriceUpdate(new AveragePrice(System.currentTimeMillis(), ticker.getCryptoAbbreviation(), ticker.getAveragePrice()));
    }
}
