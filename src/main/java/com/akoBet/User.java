package com.akoBet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Arek on 04.12.2016.
 */
@Entity
@Table(name="USERS")
public class User {


    @GeneratedValue
    @Id
    private Long ID;

    @NotNull(message = "{fieldEmpty}")
    @Size(min = 4, max = 15, message = "{fieldSize}")
    @Column
    private String LOGIN;

    @NotNull (message = "{fieldEmpty}")
    @Size(min = 4, max = 20, message = "{fieldSize}")
    @Column
    private String PASSWORD;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    @Override
    public String toString() {
        return LOGIN;
    }
}