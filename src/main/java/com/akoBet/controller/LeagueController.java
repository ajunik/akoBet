package com.akoBet.controller;

import com.akoBet.entity.League;
import com.akoBet.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Arek on 06.01.2017.
 */

@Controller
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @RequestMapping(value = "/admin/addLeague", method = RequestMethod.GET)
    public String showForm(League league) {
        return "admin/game/addLeague";
    }

    @RequestMapping(value = "/admin/addLeague", method = RequestMethod.POST)
    public String add(@Valid League league, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/game/addLeague";
        } else {
            model.addAttribute("league", league);
            leagueService.save(league);
            model.addAttribute("message", "akobet.admin.league.addSuccess");
            return "message";
        }
    }

    @RequestMapping(value = "/leagues", method = RequestMethod.GET)
    public String showLeagues() {
        return "user/leagues/list";
    }
}
