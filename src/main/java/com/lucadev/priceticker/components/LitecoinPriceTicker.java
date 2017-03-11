package com.lucadev.priceticker.components;

import org.springframework.stereotype.Component;

/**
 * Litecoin price ticker
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
@Component
public class LitecoinPriceTicker extends AbstractPriceTicker {
    @Override
    public String getCryptoAbbreviation() {
        return "LTC";
    }

    @Override
    public String getFullCryptoName() {
        return "Litecoin";
    }
}
