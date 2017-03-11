package com.lucadev.priceticker.persistence.repositories;

import com.lucadev.priceticker.persistence.models.MarketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for a single market price.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
public interface MarketPriceRepository extends JpaRepository<MarketPrice, Long> {
}
