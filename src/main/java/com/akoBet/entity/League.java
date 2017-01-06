package com.akoBet.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Arek on 06.01.2017.
 */
@Entity
@Table(name = "LEAGUES")
public class League {

    @Id
    @GeneratedValue(generator = "league_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "league_id", sequenceName = "league_id_seq")
    private Long id;
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Min(value = 4)
    @Max(value = 20)
    private Integer capacity;

    private Integer busyPlaces = 0;

    public Integer getBusyPlaces() {
        return busyPlaces;
    }

    public void setBusyPlaces(Integer busyPlaces) {
        this.busyPlaces = busyPlaces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
