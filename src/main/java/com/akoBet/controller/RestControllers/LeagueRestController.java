package com.akoBet.controller.RestControllers;

import com.akoBet.entity.League;
import com.akoBet.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Arek on 06.01.2017.
 */
@Controller
public class LeagueRestController {

    @Autowired
    private LeagueService leagueService;

    @RequestMapping(value = "rest/leagues", method = RequestMethod.GET)
    public
    @ResponseBody
    List<League> read() {
        List<League> leagues = leagueService.findAll();
        return leagues;
    }
}
