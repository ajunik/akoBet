package com.akoBet.controller.RestControllers;

import com.akoBet.entity.Bookmaker;
import com.akoBet.services.BookmakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Arek on 20.12.2016.
 */
@Controller
public class MapRestController {

    @Autowired
    BookmakerService bookmakerService;


    @RequestMapping(value = "/map")
    public String getMap() {
        return "user/map/map";
    }

    @RequestMapping(value = "rest/map", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Bookmaker> read() {
        List<Bookmaker> bookmakers = bookmakerService.findAll();
        return bookmakers;
    }

}
