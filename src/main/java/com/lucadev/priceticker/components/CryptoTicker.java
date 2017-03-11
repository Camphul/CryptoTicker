package com.lucadev.priceticker.components;

import java.util.List;

/**
 * Interface for crypto price tickers.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
public interface CryptoTicker {

    /**
     * Get all the sources for the ticker
     * @return
     */
    List<Market> getMarkets();

    /**
     * Add a new source for prices
     * @param market
     */
    void addMarket(Market market);

    /**
     * Get source by name
     * @param market
     * @return
     */
    Market getMarketByName(String market);

    /**
     * Get the abbreviation of the crypto currency like BTC/LTC/DOGE
     * @return
     */
    String getCryptoAbbreviation();

    /**
     * Full crypto name like Bitcoin/Litecoin
     * @return
     */
    String getFullCryptoName();

    /**
     * Get average price from all price sources
     * @return
     */
    double getAveragePrice();

    /**
     * Refresh all prices
     * @return
     */
    double refreshPrices();


    /**
     * Last refresh time.
     * @return
     */
    long getLastRefreshTime();
}
