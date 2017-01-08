package com.akoBet.controller;

import com.akoBet.entity.League;
import com.akoBet.entity.User;
import com.akoBet.services.LeagueService;
import com.akoBet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/addLeague", method = RequestMethod.GET)
    public String showForm(League league) {
        return "admin/game/addLeague";
    }

    @RequestMapping(value = "/admin/addLeague", method = RequestMethod.POST)
    public String add(@Valid League league, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || !checkUnique(league, bindingResult)) {
            return "admin/game/addLeague";
        } else {
            model.addAttribute("league", league);
            leagueService.save(league);
            model.addAttribute("message", "akobet.admin.league.addSuccess");
            return "message";
        }
    }

    private boolean checkUnique(League league, BindingResult bindingResult) {
        League leagueByName = leagueService.findByName(league.getName());

        if (leagueByName != null) {
            bindingResult.rejectValue("name", "akobet.league.nameExists", "League just exists");
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/leagues", method = RequestMethod.GET)
    public String showLeagues() {
        return "user/leagues/list";
    }

    @RequestMapping(value = "/leagues/{id}/join")
    public String joinLeague(@PathVariable Long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        League league = leagueService.findById(id);

        if (league == null) {
            model.addAttribute("message", "akobet.league.notExists");
            return "message";
        } else if (user.getLeague() == null) {
            user.setLeague(league);
            userService.save(user);
            Integer busyPlaces = league.getBusyPlaces();
            league.setBusyPlaces(busyPlaces + 1);
            leagueService.save(league);
            model.addAttribute("message", "akobet.league.userJoined");
            return "message";
        } else {
            model.addAttribute("message", "akobet.league.userAlreadyHasLeague");
            return "message";
        }
    }

    @RequestMapping(value = "/leagues/{id}/show/", method = RequestMethod.GET)
    public String getLeague(@PathVariable Long id, Model model) {
        League league = leagueService.findById(id);
        model.addAttribute("title", league.getName());
        model.addAttribute("id", league.getId());
        return "user/leagues/league";
    }
}
