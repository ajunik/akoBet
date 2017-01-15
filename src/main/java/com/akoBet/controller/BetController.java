package com.akoBet.controller;

import com.akoBet.entity.Bet;
import com.akoBet.entity.User;
import com.akoBet.services.MatchService;
import com.akoBet.services.PlayerTypesService;
import com.akoBet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Arek on 14.01.2017.
 */
@Controller
public class BetController {

    @Autowired
    UserService userService;

    @Autowired
    MatchService matchService;

    @Autowired
    PlayerTypesService playerTypesService;

    @RequestMapping(value = "/bet", method = RequestMethod.GET)
    public String showForm(Bet bet, Model model) {
        User logUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserById(logUser.getId());

        if(user.getLeague() == null) {
            model.addAttribute("message", "akobet.betting.notAccess");
            model.addAttribute("link", "/");
            model.addAttribute("linkMessage", "akobet.home.go");
            return "message";
        } else {
            model.addAttribute("league", user.getLeague().getId());
            model.addAttribute("rounds", matchService.getActualRound(user.getLeague()));
            model.addAttribute("user", user.getId());
        }
        return "user/betting/betting";
    }


    @RequestMapping(value = "/bet", method = {RequestMethod.PUT, RequestMethod.POST})
    public String add(@Valid Bet bet, BindingResult bindingResult, Model model) {

        User logUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserById(logUser.getId());
        playerTypesService.saveBet(bet, user);

        model.addAttribute("message", "akobet.betting.success");
        model.addAttribute("link", "bet");
        model.addAttribute("linkMessage", "akobet.betting.redirect");
        return "message";
    }

}
