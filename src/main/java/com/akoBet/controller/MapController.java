package com.akoBet.controller;

import com.akoBet.entity.Bookmaker;
import com.akoBet.services.BookmakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Arek on 13.01.2017.
 */
@Controller
public class MapController {

    @Autowired
    BookmakerService bookmakerService;

    @RequestMapping(value = "/admin/map/add", method = RequestMethod.GET)
    public String showForm(Bookmaker bookmaker) {
        return "admin/map/addBook";
    }

    @RequestMapping(value = "/admin/map/add", method = {RequestMethod.PUT, RequestMethod.POST})
    public String add(@Valid Bookmaker bookmaker, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/map/addBook";
        } else {
            bookmakerService.save(bookmaker);
            model.addAttribute("message", "akobet.map.addSuccess");
            return "message";
        }
    }

    @RequestMapping(value = "/admin/map/manage", method = RequestMethod.GET)
    public String showBooks() {
        return "admin/map/manageBooks";
    }


    @RequestMapping(value = "/admin/map/delete/{id}")
    public String deleteNews(@PathVariable Long id, Model model) {

        if (bookmakerService.findById(id) == null) {
            model.addAttribute("message", "akobet.map.notExists");
        } else {
            bookmakerService.deleteById(id);
            model.addAttribute("message", "akobet.map.deleteSuccess");
        }
        return "message";

    }

}
