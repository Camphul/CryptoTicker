package com.lucadev.priceticker.components;

/**
 * Interface for a market
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
public interface Market {

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
