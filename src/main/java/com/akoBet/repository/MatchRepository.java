package com.akoBet.repository;

import com.akoBet.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Arek on 09.01.2017.
 */
public interface MatchRepository extends JpaRepository<Match, Long> {
}
