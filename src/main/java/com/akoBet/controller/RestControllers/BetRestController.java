package com.akoBet.controller.RestControllers;

import com.akoBet.entity.Match;
import com.akoBet.entity.RestTypes;
import com.akoBet.services.MatchService;
import com.akoBet.services.PlayerTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Arek on 14.01.2017.
 */
@Controller
public class BetRestController {

    @Autowired
    MatchService matchService;

    @Autowired
    PlayerTypesService playerTypesService;

    @RequestMapping(value = "rest/matches/{leagueId}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Match> readScheduler(@PathVariable Long leagueId) {
        List<Match> duels = matchService.findByLeague(leagueId);
        return duels;
    }

    @RequestMapping(value = "rest/types/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<RestTypes> readTypes(@PathVariable long userId) {
        List<RestTypes> restTypes = playerTypesService.getApi(userId);
        return restTypes;
    }
}
