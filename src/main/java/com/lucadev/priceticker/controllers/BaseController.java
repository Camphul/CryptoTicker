package com.lucadev.priceticker.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Luca
 * @since 3/6/2017
 */
@RequestMapping("/")
public abstract class BaseController {

    @Autowired
    private Logger logger;

    public Logger getLogger() {
        return logger;
    }
}
