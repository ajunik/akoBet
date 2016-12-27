package com.akoBet.services;

import com.akoBet.entity.User;

/**
 * Created by Arek on 17.12.2016.
 */
public interface UserService {

    public User findUserByUsername(String username);

    public User findUserById(Long id);

    public User findUserByConfirmationId(String confirmationId);

    public User findUserByEmail(String email);

    public User save(User user);
}
