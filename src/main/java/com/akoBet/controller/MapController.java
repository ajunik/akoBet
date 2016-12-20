package com.akoBet.controller;

import com.akoBet.entity.Bookmaker;
import com.akoBet.repository.BookmakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 20.12.2016.
 */
@Controller
public class MapController {

    @Autowired
    BookmakerRepository bookmakerRepository;

    @RequestMapping("/map")
    String index(Model model) {
        model.addAttribute("books", getAll());
        return "map";
    }

    List<Bookmaker> getAll() {
        List<Bookmaker> bookmakers = new ArrayList<Bookmaker>();
        return bookmakers;
    }

}
