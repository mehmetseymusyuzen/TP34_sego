package org.team_project.uni_lodz_park_area.controller;

import org.team_project.uni_lodz_park_area.payload.request.auth.LoginRequest;
import org.team_project.uni_lodz_park_area.payload.request.auth.SignupRequest;
import org.team_project.uni_lodz_park_area.payload.request.auth.TokenRefreshRequest;
import org.team_project.uni_lodz_park_area.payload.response.CustomResponse;
import org.team_project.uni_lodz_park_area.payload.response.auth.JWTResponse;
import org.team_project.uni_lodz_park_area.payload.response.auth.TokenRefreshResponse;
import org.team_project.uni_lodz_park_area.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class named {@link AuthController} for authentication operations.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API for authentication operations such as register, login, refresh token, and logout.")
public class AuthController {

    private final AuthService authService;

    /**
     * Registers a new user.
     *
     * @param request The SignupRequest object containing user details.
     * @return A CustomResponse containing a success message.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user", description = "Registers a new user and returns a success message.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User registered successfully",
                            content = @Content(schema = @Schema(implementation = CustomResponse.class)))
            })
    public CustomResponse<String> register(@RequestBody SignupRequest request) {

        return CustomResponse.created(authService.register(request));
    }

    /**
     * Logs in a user and returns JWT tokens.
     *
     * @param request The LoginRequest object containing user credentials.
     * @return A CustomResponse containing JWT tokens.
     */
    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Logs in a user and returns JWT tokens.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User logged in successfully",
                            content = @Content(schema = @Schema(implementation = CustomResponse.class)))
            })
    public CustomResponse<JWTResponse> login(@RequestBody LoginRequest request) {

        return CustomResponse.ok(authService.login(request));
    }

    /**
     * Refreshes the JWT authentication token using a refresh token.
     *
     * @param request The TokenRefreshRequest object containing the refresh token.
     * @return A CustomResponse containing the refreshed token.
     */
    @PostMapping("/refreshtoken")
    @Operation(summary = "Refresh token", description = "Refreshes the JWT authentication token using a refresh token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Token refreshed successfully",
                            content = @Content(schema = @Schema(implementation = CustomResponse.class)))
            })
    public CustomResponse<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {

        return CustomResponse.ok(authService.refreshToken(request));
    }

    /**
     * Logs out a user by invalidating the authentication token.
     *
     * @param token The JWT token to invalidate.
     * @return A CustomResponse containing a success message.
     */
    @PostMapping("/logout")
    @Operation(summary = "Logout user", description = "Logs out a user by invalidating the authentication token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User logged out successfully",
                            content = @Content(schema = @Schema(implementation = CustomResponse.class)))
            },
            security = @SecurityRequirement(name = "bearerAuth"))
    public CustomResponse<String> logout(@RequestHeader("Authorization") String token) {

        return CustomResponse.ok(authService.logout(token));
    }

}
