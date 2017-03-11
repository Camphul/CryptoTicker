package com.lucadev.priceticker.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Appication config
 * @author Luca
 * @since 3/6/2017
 */
@Configuration
@EnableScheduling
@EnableJpaRepositories("com.lucadev.priceticker.persistence.repositories")
public class ApplicationConfiguration {

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("lucadev");
    }

}
