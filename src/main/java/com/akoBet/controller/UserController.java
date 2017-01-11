package com.akoBet.controller;

import com.akoBet.entity.User;
import com.akoBet.entity.UserRole;
import com.akoBet.services.EmailService;
import com.akoBet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 09.12.2016.
 */

@Controller
public class UserController extends WebMvcConfigurerAdapter {


    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/profil/{id}", method = RequestMethod.GET)
    public ModelAndView showProfil(@PathVariable Long id, Model model) {
        ModelAndView mav;
        User user = userService.findUserById(id);
        if (user == null) {
            mav = new ModelAndView("message");
            mav.addObject("message", "akobet.user.notExists");
            return mav;
        } else {
            mav = new ModelAndView("user/profil/profil");
            mav.addObject("user", user);
            if (user.getLeague() != null) {
                mav.addObject("league", user.getLeague().getName());
            } else {
                mav.addObject("league", "-");
            }
            String stats = user.getStats() + "%";
            mav.addObject("stats", stats);
        }
//        if(user.getTypesFull() != 0) {
//            double percentStats = (user.getTypesCorrect() / user.getTypesFull()) * 100.0;
//            mav.addObject("stats", percentStats);
//        } else {
//            mav.addObject("stats", 0.0);
//        }

        return mav;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showForm(User register) {
        return "user/forms/register";
    }

    @RequestMapping("/confirm")
    public String confirmation(@RequestParam(value = "id", required = true) String confirmationId, Model model) {

        User user = userService.findUserByConfirmationId(confirmationId);
        String message = "akobet.register.invalidConfirm";
        if (user != null) {
            if (!user.isConfirmationStatus()) {
                user.setConfirmationStatus(true);
                user.setConfirmationId(null);
                userService.save(user);
            }
            message = "akobet.register.success";
        }

        model.addAttribute("message", message);
        return "message";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String checkPerson(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || !checkUnique(user, bindingResult) || !processPasswords(user, bindingResult)) {
            return "user/forms/register";
        }

        List<UserRole> ur = new ArrayList<UserRole>();
        ur.add(new UserRole("ROLE_USER"));

        for (UserRole urole : ur) {
            user.addUserRole(urole);
        }
        model.addAttribute("user", user);
        user.setConfirmationId(createConfirmationID());
        emailService.send(user.getEmail(), "AkoBet Account Confirmation Link",
                "http://localhost:8080/confirm?id=" + user.getConfirmationId());
        userService.save(user);
        model.addAttribute("message", "akobet.register.checkEmail");
        return "message";
    }

    private boolean processPasswords(User user, BindingResult bindingResult) {
        if (!user.getPassword1().equals(user.getPassword2())) {
            bindingResult.rejectValue("password1", "akobet.register.passwordsAreDifferent", "Passwords don't match");
            bindingResult.rejectValue("password2", "akobet.register.passwordsAreDifferent", "Passwords don't match");
            return false;
        }
        user.setPasswordEncrypted(passwordEncoder.encode(user.getPassword1()));
        return true;
    }

    private boolean checkUnique(User user, BindingResult bindingResult) {
        User userByMail = userService.findUserByEmail(user.getEmail());
        User userByName = userService.findUserByUsername(user.getUsername());

        if (userByMail != null) {
            bindingResult.rejectValue("email", "akobet.register.emailExists", "Email just exists");
            return false;
        } else if (userByName != null) {
            bindingResult.rejectValue("username", "akobet.register.usernameExists", "Username just exists");
            return false;
        }
        return true;
    }

    private String createConfirmationID() {
        return java.util.UUID.randomUUID().toString();
    }
}
