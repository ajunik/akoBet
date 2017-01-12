package com.akoBet.services;

import com.akoBet.entity.Duel;

/**
 * Created by Arek on 09.01.2017.
 */
public interface DuelService {

    public Duel save(Duel duel);

    public Duel findById(Long id);

    public Duel findByRound(Integer round);
}
