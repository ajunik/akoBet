package com.akoBet.services;

import com.akoBet.entity.Duel;
import com.akoBet.repository.DuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Arek on 09.01.2017.
 */
@Service
public class DuelServiceImpl implements DuelService {

    @Autowired
    DuelRepository duelRepository;


    @Override
    public Duel save(Duel duel) {
        return duelRepository.saveAndFlush(duel);
    }

    @Override
    public Duel findById(Long id) {
        return duelRepository.findOne(id);
    }

    @Override
    public Duel findByRound(Integer round) {
        return duelRepository.findByRound(round);
    }
}
