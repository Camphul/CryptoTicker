package com.lucadev.priceticker.persistence.repositories;

import com.lucadev.priceticker.persistence.models.AveragePrice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Luca
 * @since 3/6/2017
 */
public interface AveragePriceRepository extends JpaRepository<AveragePrice, Long> {
}
