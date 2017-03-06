package com.lucadev.priceticker.exception;

/**
 * @author Luca
 * @since 3/6/2017
 */
public class UnsupportedCurrencyException extends Exception {

    public UnsupportedCurrencyException(String currency) {
        super(String.format("Currency %s is not supported!", currency));
    }
}
