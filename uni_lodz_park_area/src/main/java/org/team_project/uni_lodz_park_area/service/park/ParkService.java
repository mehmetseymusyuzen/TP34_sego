package org.team_project.uni_lodz_park_area.service.park;

import org.team_project.uni_lodz_park_area.model.dto.request.park.ParkCheckInRequest;
import org.team_project.uni_lodz_park_area.model.dto.request.park.ParkCheckOutRequest;
import org.team_project.uni_lodz_park_area.model.dto.response.park.ParkCheckInResponse;
import org.team_project.uni_lodz_park_area.model.dto.response.park.ParkCheckOutResponse;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;

/**
 * Service interface named {@link ParkService} for managing park operations.
 */
public interface ParkService {

    /**
     * Checks in a vehicle to the parking area.
     *
     * @param userId      the ID of the user
     * @param requestBody the check-in request body
     * @return the check-in response
     */
    ParkCheckInResponse checkIn(final String userId, final ParkCheckInRequest requestBody);

    /**
     * Counts the number of current parks in the parking area.
     *
     * @param parkingAreaEntity the parking area entity
     * @return the number of current parks
     */
    Integer countCurrentParks(ParkingAreaEntity parkingAreaEntity);

    /**
     * Checks out a vehicle from the parking area.
     *
     * @param userId              the ID of the user
     * @param parkCheckOutRequest the check-out request
     * @return the check-out response
     */
    ParkCheckOutResponse checkOut(
            final String userId,
            final ParkCheckOutRequest parkCheckOutRequest
    );

}
