package com.akoBet.services;

import com.akoBet.entity.User;
import com.akoBet.entity.UserRole;

/**
 * Created by Arek on 13.01.2017.
 */
public interface UserRoleService {

    public void delete(UserRole userRole);

    public UserRole findByUser(User user);
}
