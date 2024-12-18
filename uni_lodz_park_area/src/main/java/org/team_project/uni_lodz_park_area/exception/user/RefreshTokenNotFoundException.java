package org.team_project.uni_lodz_park_area.exception.user;

import org.team_project.uni_lodz_park_area.exception.NotFoundException;

import java.io.Serial;

/**
 * Exception class named {@link RefreshTokenNotFoundException} thrown when a refresh token is not found.
 */
public class RefreshTokenNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 7719065931723096729L;

    private static final String DEFAULT_MESSAGE =
            "The specified Refresh Token is not found!";

    public RefreshTokenNotFoundException(String message) {
        super(message);
    }

    public RefreshTokenNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
