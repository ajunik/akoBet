package com.akoBet.repository;

import com.akoBet.entity.Duel;
import com.akoBet.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Arek on 09.01.2017.
 */
public interface DuelRepository extends JpaRepository<Duel, Long> {


    public List<Duel> findByRound(Integer round);

    public List<Duel> findByLeague(League leage);
}
