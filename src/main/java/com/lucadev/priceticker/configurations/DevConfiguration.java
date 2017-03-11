package com.lucadev.priceticker.configurations;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.h2.server.web.WebServlet;
/**
 * Configuration that's only enabled when the dev profile is enabled.
 * This configuration enables stuff like the H2 web-console.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 11/3/2017
 */
@Configuration
@Profile("dev")
public class DevConfiguration {

    /**
     * Registers h2 console servlet.
     *
     * @return h2 servlet registration bean.
     */
    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
