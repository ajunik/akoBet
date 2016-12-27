package com.akoBet.controller;

import com.akoBet.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Arek on 27.12.2016.
 */
@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;
}
