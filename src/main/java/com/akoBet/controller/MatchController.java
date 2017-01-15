package com.akoBet.controller;

import com.akoBet.entity.Bet;
import com.akoBet.entity.League;
import com.akoBet.entity.Match;
import com.akoBet.entity.Set;
import com.akoBet.services.LeagueService;
import com.akoBet.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Arek on 14.01.2017.
 */
@Controller
public class MatchController {


    @Autowired
    MatchService matchService;

    @Autowired
    LeagueService leagueService;


    @RequestMapping(value = "/admin/addMatches", method = RequestMethod.GET)
    public String showForm(Set set, Model model) {
        model.addAttribute("errorMessage", "akobet.addMatches.errorEmpty");
        return "admin/game/addMatches";
    }

    @RequestMapping(value = "/admin/addMatches", method = {RequestMethod.PUT, RequestMethod.POST})
    public String add(@Valid Set set, BindingResult bindingResult, Model model) throws ParseException {
        String checkDate = checkDate(set, bindingResult);
        String checkRound = checkRound(set, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "akobet.addMatches.invalid");
            set.setLeagueId(null);
            return "admin/game/addMatches";
        } else if (!checkDate.equals("")) {
            model.addAttribute("errorMessage", checkDate);
            set.setLeagueId(null);
            return "admin/game/addMatches";
        } else if (!checkRound.equals("")) {
            model.addAttribute("errorMessage", checkRound);
            set.setLeagueId(null);
            return "admin/game/addMatches";
        } else {
            List<Match> matches = matchService.init(set);
            for (Match match : matches) {
                matchService.save(match);
            }
        }


        model.addAttribute("message", "akobet.match.addSuccess");
        model.addAttribute("link", "addMatches");
        model.addAttribute("linkMessage", "akobet.match.add.redirect");
        return "message";
    }

    public String checkDate(Set set, BindingResult bindingResult) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        if (set.getDate() == "") {
            return "akobet.addMatches.invalid";
        } else if (df.parse(set.getDate()).before(new Date())) {
            return "akobet.addMatches.invalidDate";
        }
        return "";
    }

    public String checkRound(Set set, BindingResult bindingResult) {


        if (set.getRound() == null) {
            return "akobet.addMatches.invalid";
        } else if (set.getRound() < 1) {
            return "Min.set.round";
        } else if (set.getLeagueId() != null) {
            League league = leagueService.findById(set.getLeagueId());
            if (set.getRound() >= league.getCapacity()) {

                return "akobet.addMatches.invalidRound";
            }
        }
        return "";
    }


    @RequestMapping(value = "/admin/addResults", method = RequestMethod.GET)
    public String showForm(Bet bet) {
        return "admin/game/addResult";
    }


    @RequestMapping(value = "/admin/addResults", method = {RequestMethod.PUT, RequestMethod.POST})
    public String addResults(@Valid Bet bet, BindingResult bindingResult, Model model) {

        matchService.setResults(bet);


        model.addAttribute("message", "akobet.addResults.success");
        model.addAttribute("link", "/");
        model.addAttribute("linkMessage", "akobet.home.go");
        return "message";
    }
}

