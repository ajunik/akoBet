package com.akoBet.repository;

import com.akoBet.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arek on 04.12.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    public User getUserByUsername(String username);

    public User getUserById(Long id);

    public User getUserByConfirmationId(String confirmationId);

    public User getUserByEmail(String email);
}
