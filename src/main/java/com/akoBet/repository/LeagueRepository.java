package com.akoBet.repository;

import com.akoBet.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arek on 06.01.2017.
 */
@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

    public League findByName(String name);
}
