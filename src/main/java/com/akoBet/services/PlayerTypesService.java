package com.akoBet.services;

import com.akoBet.entity.Match;
import com.akoBet.entity.User;

/**
 * Created by Arek on 09.01.2017.
 */
public interface PlayerTypesService {

    char findTypeByMatchAndUser(Match match, User user);
}
