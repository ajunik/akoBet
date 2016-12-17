package com.akoBet.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Arek on 04.12.2016.
 */
@Entity
@Table(name="USERS")
public class User extends org.springframework.security.core.userdetails.User implements UserDetails {

    public User() {
        super("NONE", "NONE", false, false, false, false, new ArrayList<GrantedAuthority>());
    }

    @Id
    @GeneratedValue(generator = "user_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_id", sequenceName = "user_id_seq")
    private Long id;


    @Size(min = 4, max = 15)
    @Column(unique = true)
    private String username;


    @Transient
    @Size(min = 5, max = 30)
    private String password1;

    @Transient
    private String password2;

    private String passwordEncrypted;

    @Size(min = 5)
    @Column(unique = true)
    private String email;
    private Date registrationDate;
    private String typesStats;
    private String matchStats;
    private String favoriteClub;
    private String league;
    private String level;
    private boolean confirmationStatus;
    private String confirmationId;
    private String createdDate;
    private String updatedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, targetEntity = UserRole.class)
    List<UserRole> UserRole = new ArrayList<UserRole>();


    @Transient
    String pattern = "dd/MM/yyyy";
    @Transient
    SimpleDateFormat format = new SimpleDateFormat(pattern);

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date lastLogin;

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = format.format(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = format.format(new Date());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(boolean confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getTypesStats() {
        return typesStats;
    }

    public void setTypesStats(String typeStats) {
        this.typesStats = typeStats;
    }

    public String getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(String matchStats) {
        this.matchStats = matchStats;
    }

    public String getFavoriteClub() {
        return favoriteClub;
    }

    public void setFavoriteClub(String favoriteClub) {
        this.favoriteClub = favoriteClub;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }


    public List<UserRole> getUserRole() {
        return UserRole;
    }

    public void setUserRole(List<UserRole> UserRole) {
        for (UserRole ar : UserRole) {
            this.addUserRole(ar);
        }
    }

    public void addUserRole(UserRole UserRole) {
        if (!this.UserRole.contains(UserRole)) {
            UserRole.setUser(this);
            this.UserRole.add(UserRole);
        }
    }
    
    @Override
    public boolean isEnabled() {
        return isConfirmationStatus();
    }

    @Override
    public Collection getAuthorities() {
        Set ga = new HashSet();
        for (UserRole ur : this.UserRole) {
            ga.add(new SimpleGrantedAuthority(ur.getRole()));
        }
        return ga;
    }

    @Override
    public String getPassword() {
        return passwordEncrypted;
    }

    public void setPassword(String password) {
        this.passwordEncrypted = password;
    }


    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public String toString() {
        return username;
    }
}