package com.akoBet.controller.RestControllers;

import com.akoBet.entity.DuelRest;
import com.akoBet.entity.UserRest;
import com.akoBet.services.DuelService;
import com.akoBet.services.LeagueService;
import com.akoBet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * Created by Arek on 08.01.2017.
 */
@Controller
public class PlayersRestController {

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private UserService userService;

    @Autowired
    private DuelService duelService;

    @RequestMapping(value = "rest/players/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<UserRest> read(@PathVariable Long id) {
        List<UserRest> players = leagueService.getPlayersApi(id);
        players.sort((o1, o2) -> o1.getPoints().compareTo(o2.getPoints()));
        Collections.reverse(players);
        return players;
    }

    @RequestMapping(value = "rest/scheduler/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<DuelRest> readScheduler(@PathVariable Long id) {
        List<DuelRest> duels = duelService.getDuelsApi(id);
        return duels;
    }


}