package com.akoBet.entity;

import javax.persistence.*;

/**
 * Created by Arek on 09.01.2017.
 */
@Entity
@Table(name = "PLAYER_TYPES")
public class PlayerTypes {

    @Id
    @GeneratedValue(generator = "playerTypes_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "playerTypes_id", sequenceName = "playerTypes_id_seq")
    private Long id;
    private char type;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "playerId", nullable = true)
    private User user;

    @OneToOne(targetEntity = Match.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "matchId", nullable = true)
    private Match match;
}
