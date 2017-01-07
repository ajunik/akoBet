package com.akoBet.services;

import com.akoBet.entity.User;
import com.akoBet.entity.UserRest;

import java.util.List;

/**
 * Created by Arek on 17.12.2016.
 */
public interface UserService {

    public List<User> findAll();

    public List<UserRest> getUserApi();

    public UserRest getUserApiById(Long id);

    public User findUserByUsername(String username);

    public User findUserById(Long id);

    public User findUserByConfirmationId(String confirmationId);

    public User findUserByEmail(String email);

    public List<User> findUsersByLeague(Long leagueId);

    public User save(User user);

    public void deleteById(Long id);
}
