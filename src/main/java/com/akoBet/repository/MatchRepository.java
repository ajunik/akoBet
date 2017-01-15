package com.akoBet.repository;

import com.akoBet.entity.League;
import com.akoBet.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByLeague(League league);
}
