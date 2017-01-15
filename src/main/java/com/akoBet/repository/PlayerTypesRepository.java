package com.akoBet.repository;

import com.akoBet.entity.Match;
import com.akoBet.entity.PlayerTypes;
import com.akoBet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
public interface PlayerTypesRepository extends JpaRepository<PlayerTypes, Long> {

    public PlayerTypes findByMatchAndUser(Match match, User user);

    public List<PlayerTypes> findByUser(User user);

}
