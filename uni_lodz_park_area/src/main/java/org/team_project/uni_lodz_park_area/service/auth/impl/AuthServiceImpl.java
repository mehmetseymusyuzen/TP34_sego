package org.team_project.uni_lodz_park_area.service.auth.impl;

import org.team_project.uni_lodz_park_area.exception.user.EmailAlreadyExistsException;
import org.team_project.uni_lodz_park_area.exception.user.RefreshTokenNotFoundException;
import org.team_project.uni_lodz_park_area.exception.user.UserNotFoundException;
import org.team_project.uni_lodz_park_area.payload.request.auth.LoginRequest;
import org.team_project.uni_lodz_park_area.payload.request.auth.SignupRequest;
import org.team_project.uni_lodz_park_area.payload.request.auth.TokenRefreshRequest;
import org.team_project.uni_lodz_park_area.payload.response.auth.JWTResponse;
import org.team_project.uni_lodz_park_area.payload.response.auth.TokenRefreshResponse;
import org.team_project.uni_lodz_park_area.security.CustomUserDetails;
import org.team_project.uni_lodz_park_area.security.jwt.JwtUtils;
import org.team_project.uni_lodz_park_area.security.model.entity.RefreshToken;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.team_project.uni_lodz_park_area.security.repository.UserRepository;
import org.team_project.uni_lodz_park_area.service.auth.AuthService;
import org.team_project.uni_lodz_park_area.service.auth.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class named {@link AuthServiceImpl} that provides authentication-related functionalities.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final RefreshTokenService refreshTokenService;

    private final JwtUtils jwtUtils;

    /**
     * Registers a new user.
     *
     * @param request the signup request
     * @return the generated user ID
     */
    @Override
    public String register(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(userEntity);

        return "Success";
    }

    /**
     * Authenticates a user.
     *
     * @param request the login request
     * @return the JWT response
     */
    @Override
    public JWTResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtUtils.generateJwtToken(auth);

        UserEntity userEntity = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);

        return JWTResponse.builder()
                .email(request.getEmail())
                .token(jwtToken)
                .refreshToken(refreshTokenService.createRefreshToken(userEntity))
                .build();
    }

    /**
     * Refreshes the JWT token.
     *
     * @param request the token refresh request
     * @return the token refresh response
     */
    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {

        RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken())
                .orElseThrow(RefreshTokenNotFoundException::new);


        if (!refreshTokenService.isRefreshExpired(refreshToken)) {
            CustomUserDetails customUserDetails = new CustomUserDetails(refreshToken.getUserEntity());
            String newToken = jwtUtils.generateJwtToken(customUserDetails);

            return TokenRefreshResponse.builder()
                    .accessToken(newToken)
                    .refreshToken(refreshToken.getToken())
                    .build();
        }

        return null;
    }

    /**
     * Logs out the user.
     *
     * @param token the user's token
     * @return the result of the logout operation
     */
    @Override
    public String logout(String token) {

        String authToken = jwtUtils.extractTokenFromHeader(token);

        if (jwtUtils.validateJwtToken(authToken)) {
            String id = jwtUtils.getIdFromToken(authToken);

            refreshTokenService.deleteByUserId(id);

            return "Success";
        }

        return "Failed";

    }

}