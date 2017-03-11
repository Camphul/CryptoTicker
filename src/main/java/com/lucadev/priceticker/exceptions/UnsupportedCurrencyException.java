package com.lucadev.priceticker.exceptions;

/**
 * Runtime exception that gets thrown when a given currency is not supported by the price ticker.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
public class UnsupportedCurrencyException extends Exception {

    public UnsupportedCurrencyException(String currency) {
        super(String.format("Currency %s is not supported!", currency));
    }
}
