package org.team_project.uni_lodz_park_area.service.auth;

import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;

import java.util.Optional;

/**
 * Service interface named {@link UserService} for user-related operations.
 */
public interface UserService {

    /**
     * Retrieves a user by username.
     *
     * @param username the username
     * @return an {@link Optional} containing the user, or empty if not found
     */
    Optional<UserEntity> findByUsername(String username);

    /**
     * Retrieves a user by ID.
     *
     * @param userId the user ID
     * @return an {@link Optional} containing the user, or empty if not found
     */
    Optional<UserEntity> findById(String userId);

    /**
     * Retrieves a user by email.
     *
     * @param email the email address
     * @return an {@link Optional} containing the user, or empty if not found
     */
    Optional<UserEntity> findByEmail(String email);

}
