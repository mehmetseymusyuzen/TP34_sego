package org.team_project.uni_lodz_park_area.exception.parkingarea;

import org.team_project.uni_lodz_park_area.exception.NotFoundException;

import java.io.Serial;

/**
 * Exception class named {@link ParkingAreaNotFoundException} thrown when a parking area is not found.
 */
public class ParkingAreaNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = -75325461433448105L;

    private static final String DEFAULT_MESSAGE =
            "The specified ParkingAreaEntity is not found!";

    private static final String MESSAGE_TEMPLATE =
            "No ParkingAreaEntity found with ID: ";

    public ParkingAreaNotFoundException(String id) {
        super(MESSAGE_TEMPLATE.concat(id));
    }

    public ParkingAreaNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
