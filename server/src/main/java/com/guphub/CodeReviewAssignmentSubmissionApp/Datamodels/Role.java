package com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    private String authority;


    public Role(){
        super();
    }

    public Role(String authority){
        this.authority=authority;
    }

    public Role(Long role_id, String authority) {
        this.id = role_id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


}
