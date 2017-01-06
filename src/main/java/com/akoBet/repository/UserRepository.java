package com.akoBet.repository;

import com.akoBet.entity.User;
import com.akoBet.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Arek on 04.12.2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByUsername(String username);

    public User findUserById(Long id);

    public User findUserByConfirmationId(String confirmationId);

    public User findUserByEmail(String email);

    public List<UserRole> findUserRolesById(Long id);
}
