package com.akoBet.repository;

import com.akoBet.entity.Duel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Arek on 09.01.2017.
 */
public interface DuelRepository extends JpaRepository<Duel, Long> {


    public Duel findByRound(Integer round);
}
