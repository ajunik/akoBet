package com.akoBet.controller;

import com.akoBet.entity.User;
import com.akoBet.repository.UserRepository;
import com.akoBet.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

/**
 * Created by Arek on 09.12.2016.
 */

@Controller
public class UserController extends WebMvcConfigurerAdapter {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showForm(User register) {
        return "register";
    }

    @RequestMapping("/confirm")
    public String confirmation(@RequestParam(value = "id", required = true) String confirmationId, Model model) {

        User user = userRepository.getUserByConfirmationId(confirmationId);
        String message = "akobet.register.invalidConfirm";
        if (user != null) {
            if (!user.isConfirmationStatus()) {
                user.setConfirmationStatus(true);
                user.setConfirmationId(null);
                userRepository.save(user);
            }
            message = user.getUsername() + "akobet.register.success";
        }

        model.addAttribute("message", message);
        return "message";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String checkPerson(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || !checkUnique(user, bindingResult) || !processPasswords(user, bindingResult)) {
            return "register";
        }

        model.addAttribute("user", user);
        user.setConfirmationId(createConfirmationID());
        emailService.send(user.getEmail(), "AkoBet Account Confirmation Link",
                "http://localhost:8080/confirm?id=" + user.getConfirmationId());
        userRepository.save(user);
        model.addAttribute("message", "Please, check your mailbox for the email confirmation link. ");
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
        User userByMail = userRepository.getUserByEmail(user.getEmail());
        User userByName = userRepository.getUserByUsername(user.getUsername());

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
