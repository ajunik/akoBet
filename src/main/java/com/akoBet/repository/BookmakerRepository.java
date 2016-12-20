package com.akoBet.repository;

import com.akoBet.entity.Bookmaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arek on 20.12.2016.
 */
@Repository
public interface BookmakerRepository extends JpaRepository<Bookmaker, Long> {
}
