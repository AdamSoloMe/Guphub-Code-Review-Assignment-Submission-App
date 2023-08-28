package com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    private Long id;
    private LocalDate cohortStartDate;
    @Column(unique = true)
    private String username;

   @JsonIgnore
    private String password;


    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="user_role_junction",
        joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}

    )
    private Set<Role>  authorities;

    public  User(){
        super();
        this.authorities= new HashSet<Role>();
    }

    public User(String username, String password, Set<Role> roles) {
        this.username=username;
        this.password=password;
        this.authorities=roles;
    }

    public User(LocalDate cohortStartDate, String username, String password, Set<Role> authorities) {
        super();
        this.cohortStartDate = cohortStartDate;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(Long id, LocalDate cohortStartDate, String username, String password, Set<Role> authorities) {
        super();
        this.id = id;
        this.cohortStartDate = cohortStartDate;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(Long id, String username, String password, Set<Role> authorities) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCohortStartDate() {
        return cohortStartDate;
    }

    public void setCohortStartDate(LocalDate cohortStartDate) {
        this.cohortStartDate = cohortStartDate;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Authories are roles
       // return List.of(new SimpleGrantedAuthority(role.name()));
        return this.authorities;
    }


}
