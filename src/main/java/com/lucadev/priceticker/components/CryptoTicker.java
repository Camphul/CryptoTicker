package com.lucadev.priceticker.components;

import java.util.List;

/**
 * @author Luca
 * @since 3/6/2017
 */
public interface CryptoTicker {

    /**
     * Get all the sources for the ticker
     * @return
     */
    List<PriceSource> getPriceSources();

    /**
     * Add a new source for prices
     * @param priceSource
     */
    void addPriceSource(PriceSource priceSource);

    /**
     * Get source by name
     * @param market
     * @return
     */
    PriceSource getSourceByName(String market);

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
