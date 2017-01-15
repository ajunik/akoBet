package com.akoBet.controller;

import com.akoBet.entity.User;
import com.akoBet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Arek on 14.01.2017.
 */
@Controller
public class BetController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/bet", method = RequestMethod.GET)
    public String showForm(Model model) {
        User logUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserById(logUser.getId());

        if(user.getLeague() == null) {
            model.addAttribute("league", 0);
        } else {
            model.addAttribute("league", user.getLeague().getId());
            model.addAttribute("rounds", user.getLeague().getCapacity());
        }
        return "user/betting/betting";
    }

}
