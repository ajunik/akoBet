package com.akoBet.controller;

import com.akoBet.entity.Administrator;
import com.akoBet.entity.User;
import com.akoBet.entity.UserRole;
import com.akoBet.repository.UserRepository;
import com.akoBet.services.EmailService;
import com.akoBet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
    UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("indexAdmin");
//        List<String> roles = new ArrayList<String>();
//        roles.add("ROLE_ADMIN");
//        List<User> users = userService.listUsers(roles);
//        mav.addObject("users", users);
        return mav;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String add(Administrator admin) {
        return "addAdmin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String add(@Valid Administrator admin, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || !checkUnique(admin, bindingResult) || !processPasswords(admin, bindingResult)) {
            return "addAdmin";
        }
        List<UserRole> ur = new ArrayList<UserRole>();
        ur.add(new UserRole("ROLE_ADMIN"));
        ur.add(new UserRole("ROLE_USER"));

        for (UserRole urole : ur) {
            admin.addUserRole(urole);
        }
        model.addAttribute("admin", admin);
        admin.setConfirmationId(createConfirmationID());
        emailService.send(admin.getEmail(), "AkoBet Account Confirmation Link",
                "http://localhost:8080/confirm?id=" + admin.getConfirmationId());
        userRepository.save(admin);
        model.addAttribute("message", "akobet.register.checkEmail");
        return "message";
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
        User userByMail = userRepository.findUserByEmail(user.getEmail());
        User userByName = userRepository.findUserByUsername(user.getUsername());

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