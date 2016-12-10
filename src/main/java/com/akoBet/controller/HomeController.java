package com.akoBet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Arek on 07.12.2016.
 */
@Controller
class HomeController {

    @RequestMapping("/")
    String index(Model model, HttpServletRequest request) {
        model.addAttribute("name", (request.getRemoteUser() == null) ? "" : request.getRemoteUser());
        return "home";
    }


}