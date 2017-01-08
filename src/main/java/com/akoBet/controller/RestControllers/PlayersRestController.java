package com.akoBet.controller.RestControllers;

import com.akoBet.entity.UserRest;
import com.akoBet.services.LeagueService;
import com.akoBet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "rest/players/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<UserRest> read(@PathVariable Long id) {
        List<UserRest> players = leagueService.getPlayersApi(id);
        return players;
    }
}