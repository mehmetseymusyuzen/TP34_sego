package org.team_project.uni_lodz_park_area.service.auth;

import org.team_project.uni_lodz_park_area.payload.request.auth.LoginRequest;
import org.team_project.uni_lodz_park_area.payload.request.auth.SignupRequest;
import org.team_project.uni_lodz_park_area.payload.request.auth.TokenRefreshRequest;
import org.team_project.uni_lodz_park_area.payload.response.auth.JWTResponse;
import org.team_project.uni_lodz_park_area.payload.response.auth.TokenRefreshResponse;

/**
 * Service interface named {@link AuthService} for user authentication.
 */
public interface AuthService {

    /**
     * Registers a new user.
     *
     * @param request the signup request
     * @return the generated user ID
     */
    String register(SignupRequest request);

    /**
     * Authenticates a user.
     *
     * @param request the login request
     * @return the JWT response
     */
    JWTResponse login(LoginRequest request);

    /**
     * Refreshes the JWT token.
     *
     * @param request the token refresh request
     * @return the token refresh response
     */
    TokenRefreshResponse refreshToken(TokenRefreshRequest request);

    /**
     * Logs out the user.
     *
     * @param token the user's token
     * @return the result of the logout operation
     */
    String logout(String token);

}
