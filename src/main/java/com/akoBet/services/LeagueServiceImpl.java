package com.akoBet.services;

import com.akoBet.entity.League;
import com.akoBet.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Arek on 06.01.2017.
 */
@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    UserService userService;

    @Override
    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    @Override
    public League findById(Long id) {
        return leagueRepository.findOne(id);
    }


    @Override
    public League findByName(String name) {
        return leagueRepository.findByName(name);
    }

    @Override
    public League save(League league) {
        return leagueRepository.saveAndFlush(league);
    }
}
