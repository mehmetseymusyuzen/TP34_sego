package org.team_project.uni_lodz_park_area.exception.vehicle;

import org.team_project.uni_lodz_park_area.exception.NotFoundException;

import java.io.Serial;

/**
 * Exception class named {@link VehicleNotFoundException} thrown when a vehicle is not found.
 */
public class VehicleNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 5431951439973055123L;

    private static final String DEFAULT_MESSAGE =
            "The specified vehicle is not found";

    private static final String MESSAGE_TEMPLATE =
            "No vehicle was found with ID: ";

    public VehicleNotFoundException(String id) {
        super(MESSAGE_TEMPLATE.concat(id));
    }

    public VehicleNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
