package org.team_project.uni_lodz_park_area.service.parking_area;

import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.dto.request.parking_area.ParkingAreaCreateRequest;

/**
 * Service interface named {@link ParkingAreaCreateService} for creating parking areas.
 */
public interface ParkingAreaCreateService {

    /**
     * Creates a new parking area.
     *
     * @param parkingAreaCreateRequest the request containing details for creating the parking area
     * @return the created parking area
     */
    ParkingArea createParkingArea(
            final ParkingAreaCreateRequest parkingAreaCreateRequest
    );

}
