package com.caronic.jwisdom.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by caronic on 2016/3/10.
 */
@Controller
public class WelcomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping("/")
    public String index(Model model) {
        LOGGER.info("Welcome to home page");
        return "index";
    }
}
