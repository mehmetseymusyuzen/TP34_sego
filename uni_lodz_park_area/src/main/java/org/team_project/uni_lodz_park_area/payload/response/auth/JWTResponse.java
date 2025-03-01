package org.team_project.uni_lodz_park_area.payload.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a JWT response named {@link JWTResponse}.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {

    private String token;

    @Builder.Default
    private String type = "Bearer";

    private String refreshToken;

    private String email;
    
    private String id;
    
    private java.util.List<String> roles;

}
