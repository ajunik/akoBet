package com.akoBet.services;

import com.akoBet.entity.User;
import com.akoBet.entity.UserRole;
import com.akoBet.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Arek on 13.01.2017.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;


    @Override
    public void delete(UserRole userRole) {
        userRoleRepository.delete(userRole);
    }

    @Override
    public UserRole findByUser(User user) {
        return userRoleRepository.findByUser(user);
    }
}
