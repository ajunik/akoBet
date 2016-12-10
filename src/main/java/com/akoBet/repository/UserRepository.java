package com.akoBet.repository;

import com.akoBet.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arek on 04.12.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    public User getUserByLogin(String login);

    public User getUserById(Long id);

    public User getUserByConfirmationId(String confirmationId);
}
