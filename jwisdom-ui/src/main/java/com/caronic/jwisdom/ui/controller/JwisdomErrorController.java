package com.caronic.jwisdom.ui.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by caronic on 2016/3/10.
 */
@Controller
public class JwisdomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping("/error")
    public String errorHandle() {
        return getErrorPath();
    }

}
