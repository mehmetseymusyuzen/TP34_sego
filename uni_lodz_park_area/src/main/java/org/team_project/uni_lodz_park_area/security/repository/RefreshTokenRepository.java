package org.team_project.uni_lodz_park_area.security.repository;

import org.team_project.uni_lodz_park_area.security.model.entity.RefreshToken;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

/**
 * Repository interface named {@link RefreshTokenRepository} for managing refresh tokens.
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    /**
     * Find a refresh token by user ID.
     *
     * @param userId The ID of the user.
     * @return The refresh token.
     */
    RefreshToken findByUserEntityId(String userId);

    /**
     * Find a refresh token by its token string.
     *
     * @param token The token string.
     * @return The refresh token.
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Delete refresh tokens associated with a user entity.
     *
     * @param userEntity The user entity.
     * @return The number of tokens deleted.
     */
    @Modifying
    int deleteByUserEntity(UserEntity userEntity);

}
