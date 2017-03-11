package com.lucadev.priceticker.components;

import org.springframework.stereotype.Component;

/**
 * Bitcoin price ticker
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
@Component
public class BitcoinPriceTicker extends AbstractPriceTicker {

    @Override
    public String getCryptoAbbreviation() {
        return "BTC";
    }

    @Override
    public String getFullCryptoName() {
        return "Bitcoin";
    }

}
