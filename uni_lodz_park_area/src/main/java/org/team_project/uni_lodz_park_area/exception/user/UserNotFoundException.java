package org.team_project.uni_lodz_park_area.exception.user;

import org.team_project.uni_lodz_park_area.exception.NotFoundException;

import java.io.Serial;

/**
 * Exception class named {@link UserNotFoundException} thrown when a user is not found.
 */
public class UserNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 4895291155273970971L;

    private static final String DEFAULT_MESSAGE =
            "The specified user is not found";

    private static final String MESSAGE_TEMPLATE =
            "No user was found with ID: ";

    public UserNotFoundException(String id) {
        super(MESSAGE_TEMPLATE.concat(id));
    }

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
