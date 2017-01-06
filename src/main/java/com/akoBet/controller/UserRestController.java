package com.akoBet.controller;

import com.akoBet.entity.UserRest;
import com.akoBet.services.UserService;
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
public class UserRestController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "rest/users", method = RequestMethod.GET)
    public
    @ResponseBody
    List<UserRest> read() {
        List<UserRest> users = userService.getUserApi();
        return users;
    }


}
