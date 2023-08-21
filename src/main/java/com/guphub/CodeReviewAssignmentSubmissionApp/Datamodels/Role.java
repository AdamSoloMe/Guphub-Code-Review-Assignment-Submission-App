package com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels;



import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AuthorityEnum;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;

    public Role() {
        super();
    }

    public Role(AuthorityEnum authority) {
        this.authority = authority;
    }

    public Role(Long roleId, AuthorityEnum authority) {
        this.id = roleId;
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
        return authority.name(); // Use the name of the enum as the authority
    }

    public AuthorityEnum getAuthorityEnum() {
        return authority;
    }

    public void setAuthorityEnum(AuthorityEnum authority) {
        this.authority = authority;
    }
}
