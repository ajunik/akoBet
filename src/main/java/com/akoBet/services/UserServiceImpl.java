package com.akoBet.services;

import com.akoBet.entity.League;
import com.akoBet.entity.User;
import com.akoBet.entity.UserRest;
import com.akoBet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 17.12.2016.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    LeagueService leagueService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserRest> getUserApi() {
        List<User> users = findAll();
        List<UserRest> restUsers = new ArrayList<>();
        for (User user : users) {
            UserRest userRest = new UserRest();
            userRest.setId(user.getId());
            userRest.setName(user.getUsername());
            userRest.setMail(user.getEmail());
            userRest.setAuth(user.getAuthorities());
            userRest.setPoints(user.getPoints());
            userRest.setMatches(user.getMatches());
            if (user.getLeague() != null) {
                userRest.setLeague(user.getLeague().getName());
            }
            userRest.setCreatedDate(user.getCreatedDate());
            restUsers.add(userRest);
        }
        return restUsers;
    }

    @Override
    public UserRest getUserApiById(Long id) {
        User user = userRepository.findOne(id);
        UserRest userRest = new UserRest();
        userRest.setId(user.getId());
        userRest.setName(user.getUsername());
        userRest.setId(user.getId());
        userRest.setName(user.getUsername());
        userRest.setMail(user.getEmail());
        userRest.setAuth(user.getAuthorities());
        userRest.setPoints(user.getPoints());
        userRest.setMatches(user.getMatches());
        if (user.getLeague() != null) {
            userRest.setLeague(user.getLeague().getName());
        }
        userRest.setCreatedDate(user.getCreatedDate());

        return userRest;
    }


    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByConfirmationId(String confirmationId) {
        return userRepository.findUserByConfirmationId(confirmationId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findUsersByLeague(Long leagueId) {
        League league = leagueService.findById(leagueId);
        List<User> users = userRepository.findUsersByLeague(league);

        return users;
    }

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteById(Long id) throws RuntimeException {
        userRepository.delete(id);
    }
}
