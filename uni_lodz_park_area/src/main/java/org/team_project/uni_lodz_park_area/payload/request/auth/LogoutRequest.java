package org.team_project.uni_lodz_park_area.payload.request.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a logout request named {@link LogoutRequest}.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {

    private String token;

}
