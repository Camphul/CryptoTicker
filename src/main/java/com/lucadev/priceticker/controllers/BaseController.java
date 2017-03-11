package com.lucadev.priceticker.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Base class for controllers.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/6/2017
 */
@RequestMapping("/")
public abstract class BaseController {

    @Autowired
    private Logger appLogger;

    public Logger getAppLogger() {
        return appLogger;
    }
}
