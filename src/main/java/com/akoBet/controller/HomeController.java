package com.akoBet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Arek on 07.12.2016.
 */
@Controller
class HomeController {

    @RequestMapping("/")
    String index() {
        return "home";
    }


}