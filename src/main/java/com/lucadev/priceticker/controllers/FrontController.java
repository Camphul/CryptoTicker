package com.lucadev.priceticker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Front-end controller for the user.
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 * @since 3/11/2017
 */
@Controller
public class FrontController extends BaseController {

    /**
     * Index page/the actual front end.
     * @return
     */
    @GetMapping(value = {"/index.html", "/", "/index"})
    public ModelAndView index() {
        Map model = new HashMap();
        return new ModelAndView("index", model);
    }
}
