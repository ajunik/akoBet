package com.akoBet.services;

import com.akoBet.entity.Bookmaker;

import java.util.List;

/**
 * Created by Arek on 27.12.2016.
 */
public interface BookmakerService {

    List<Bookmaker> findAll();
}