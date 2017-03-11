package com.lucadev.priceticker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Front-end controller for the user.
 * @author Luca
 * @since 3/11/2017
 */
@Controller
public class FrontController extends BaseController {

    @GetMapping(value = {"/index.html", "/", "/index"})
    public ModelAndView index() {
        Map view = new HashMap();
        return new ModelAndView("index", view);
    }
}
