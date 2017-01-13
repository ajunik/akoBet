package com.akoBet.repository;

import com.akoBet.entity.User;
import com.akoBet.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arek on 13.01.2017.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {


    public void delete(UserRole userRole);

    public UserRole findByUser(User user);
}
