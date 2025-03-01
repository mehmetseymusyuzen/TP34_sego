package org.team_project.uni_lodz_park_area.payload.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a token refresh request named {@link TokenRefreshRequest}.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRefreshRequest {

    @NotBlank
    private String refreshToken;

}
