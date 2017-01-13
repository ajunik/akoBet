package com.akoBet.controller;

import com.akoBet.entity.Administrator;
import com.akoBet.entity.League;
import com.akoBet.entity.User;
import com.akoBet.entity.UserRole;
import com.akoBet.services.EmailService;
import com.akoBet.services.LeagueService;
import com.akoBet.services.UserRoleService;
import com.akoBet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 17.12.2016.
 */
@Controller
public class AdministratorController extends WebMvcConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    LeagueService leagueService;

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String add(Administrator admin) {
        return "admin/users/addAdmin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String add(@Valid Administrator admin, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || !checkUnique(admin, bindingResult) || !processPasswords(admin, bindingResult)) {
            return "admin/users/addAdmin";
        }
        List<UserRole> ur = new ArrayList<UserRole>();
        ur.add(new UserRole("ROLE_ADMIN"));

        for (UserRole urole : ur) {
            admin.addUserRole(urole);
        }
        model.addAttribute("admin", admin);
        admin.setConfirmationId(createConfirmationID());
        emailService.send(admin.getEmail(), "AkoBet Account Confirmation Link",
                "http://localhost:8080/confirm?id=" + admin.getConfirmationId());
        userService.save(admin);
        model.addAttribute("message", "akobet.register.checkEmail");
        model.addAttribute("link", "/");
        model.addAttribute("linkMessage", "akobet.home.go");
        return "message";
    }

    @RequestMapping(value = "/admin/user/delete/{id}")
    public String deleteUsers(@PathVariable Long id, Model model) {

        User user = userService.findUserById(id);
        if (user == null) {
            model.addAttribute("message", "akobet.user.notExists");
        } else {
            League league = user.getLeague();
            if (league != null) {
                if (league.getBusyPlaces() == league.getCapacity()) {
                    model.addAttribute("message", "akobet.user.notDelete");
                } else {
                    Integer busyPlaces = league.getBusyPlaces();
                    league.setBusyPlaces(busyPlaces - 1);
                    leagueService.save(league);
                    user.setLeague(null);
                    UserRole userRole = userRoleService.findByUser(user);
                    userRole.setUser(null);
                    userRoleService.delete(userRole);
                    userService.deleteById(id);
                    model.addAttribute("message", "akobet.user.deleteSuccess");
                }
            } else {
                UserRole userRole = userRoleService.findByUser(user);
                userRole.setUser(null);
                userRoleService.delete(userRole);
                userService.deleteById(id);
                model.addAttribute("message", "akobet.user.deleteSuccess");
            }
        }
        model.addAttribute("link", "../../usersList");
        model.addAttribute("linkMessage", "akobet.admin.userList.redirect");
        return "message";

    }

    @RequestMapping(value = "/admin/usersList", method = RequestMethod.GET)
    public String showUsers() {
        return "admin/users/usersList";
    }

    protected boolean processPasswords(User user, BindingResult bindingResult) {
        if (!user.getPassword1().equals(user.getPassword2())) {
            bindingResult.rejectValue("password1", "akobet.register.passwordsAreDifferent", "Passwords don't match");
            bindingResult.rejectValue("password2", "akobet.register.passwordsAreDifferent", "Passwords don't match");
            return false;
        }
        user.setPasswordEncrypted(passwordEncoder.encode(user.getPassword1()));
        return true;
    }

    protected boolean checkUnique(User user, BindingResult bindingResult) {
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

    protected String createConfirmationID() {
        return java.util.UUID.randomUUID().toString();
    }
}