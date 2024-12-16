package org.team_project.uni_lodz_park_area.security.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents a refresh token entity named {@link RefreshToken}.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REFRESH_TOKEN")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @OneToOne
    @JoinColumn(
            name = "USER_ID",
            referencedColumnName = "ID"
    )
    private UserEntity userEntity;

    @Column(
            name = "TOKEN",
            nullable = false,
            unique = true
    )
    private String token;

    @Column(
            name = "EXPIRY_DATE",
            nullable = false
    )
    private LocalDate expiryDate;

}
