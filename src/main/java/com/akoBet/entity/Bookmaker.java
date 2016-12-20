package com.akoBet.entity;

import javax.persistence.*;

/**
 * Created by Arek on 20.12.2016.
 */
@Entity
@Table(name = "BOOKS")
public class Bookmaker {

    @Id
    @GeneratedValue(generator = "books_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "books_id", sequenceName = "books_id_seq")
    private Long id;

    private String name;

    private String address;

    private String openingHours;

    private double latitude;

    private double longitude;

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

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return name + " " + address;
    }
}
