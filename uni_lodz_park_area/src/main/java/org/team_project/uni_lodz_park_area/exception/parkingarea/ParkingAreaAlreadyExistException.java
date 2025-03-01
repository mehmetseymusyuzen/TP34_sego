package org.team_project.uni_lodz_park_area.exception.parkingarea;

import org.team_project.uni_lodz_park_area.exception.AlreadyException;

import java.io.Serial;

/**
 * Exception class named {@link ParkingAreaAlreadyExistException} thrown when a parking area already exists.
 */
public class ParkingAreaAlreadyExistException extends AlreadyException {

    @Serial
    private static final long serialVersionUID = -4515385870402222793L;

    private static final String DEFAULT_MESSAGE =
            "The Parking Area Name and Location already exist!";

    public ParkingAreaAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public ParkingAreaAlreadyExistException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
