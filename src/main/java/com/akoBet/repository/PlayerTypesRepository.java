package com.akoBet.repository;

import com.akoBet.entity.Match;
import com.akoBet.entity.PlayerTypes;
import com.akoBet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Arek on 09.01.2017.
 */
public interface PlayerTypesRepository extends JpaRepository<PlayerTypes, Long> {

    char findTypeByMatchAndUser(Match match, User user);

}
