package com.akoBet.entity;


import javax.persistence.*;

/**
 * Created by Arek on 17.12.2016.
 */
@Entity
@Table(name = "User_role")
public class UserRole {

    public UserRole() {
        this.setRole("ROLE_ANONYMOUS");
    }

    public UserRole(String role) {
        this.setRole(role);
    }

    @Id
    @GeneratedValue(generator = "role_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_id", sequenceName = "User_role_id_seq", initialValue = 1)
    Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = true)
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
