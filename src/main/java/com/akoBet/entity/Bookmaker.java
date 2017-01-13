package com.akoBet.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

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

    @Size(min = 1, max = 15)
    private String name;

    @Size(min = 1, max = 50)
    private String address;

    @Min(-90)
    @Max(90)
    private double latitude;

    @Min(-180)
    @Max(180)
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
