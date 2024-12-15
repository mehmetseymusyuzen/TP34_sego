package org.team_project.uni_lodz_park_area.service.parking_area;

import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.dto.request.parking_area.ParkingAreaUpdateRequest;

/**
 * Service interface named {@link ParkingAreaUpdateService} for updating parking areas.
 */
public interface ParkingAreaUpdateService {

    /**
     * Updates the capacity of a parking area.
     *
     * @param parkingAreaId           the ID of the parking area to update
     * @param parkingAreaUpdateRequest the request containing the new capacity
     * @return the updated parking area
     */
    ParkingArea parkingAreaUpdateByCapacity(
            final String parkingAreaId,
            final ParkingAreaUpdateRequest parkingAreaUpdateRequest
    );

}