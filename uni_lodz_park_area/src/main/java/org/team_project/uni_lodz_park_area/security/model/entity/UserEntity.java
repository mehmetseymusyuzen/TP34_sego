package org.team_project.uni_lodz_park_area.security.model.entity;

import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.security.model.enums.Role;
import org.team_project.uni_lodz_park_area.security.model.enums.TokenClaims;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a user entity named {@link UserEntity}.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "userEntity"
    )
    private List<VehicleEntity> vehicles;

    /**
     * Generates JWT claims for the user.
     *
     * @return Map of JWT claims.
     */
    public Map<String, Object> getClaims() {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(TokenClaims.ID.getValue(), this.id);
        claims.put(TokenClaims.USERNAME.getValue(), this.username);
        claims.put(TokenClaims.ROLES.getValue(), List.of(this.role));
        claims.put(TokenClaims.USER_FULL_NAME.getValue(), this.fullName);
        claims.put(TokenClaims.EMAIL.getValue(), this.email);
        return claims;
    }

}
