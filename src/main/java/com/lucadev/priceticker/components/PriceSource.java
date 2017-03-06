package com.lucadev.priceticker.components;

/**
 * @author Luca
 * @since 3/6/2017
 */
public interface PriceSource {

    String getSupportedCryptoCurrency();

    /**
     * Get the recent price.
     * @return
     */
    double getRecentPrice();

    /**
     * Refreshes price
     * @return
     */
    double refreshPrice();

    /**
     * Time of last updated price
     * @return
     */
    long getTimeLastUpdated();

    /**
     * Get the name of the source
     * @return
     */
    String getSourceName();

}
