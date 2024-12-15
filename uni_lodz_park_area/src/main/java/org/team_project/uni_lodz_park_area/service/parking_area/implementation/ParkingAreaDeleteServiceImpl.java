package org.team_project.uni_lodz_park_area.service.parking_area.implementation;

import org.team_project.uni_lodz_park_area.exception.parking_area.ParkingAreaNotFoundException;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.repository.ParkingAreaRepository;
import org.team_project.uni_lodz_park_area.service.parking_area.ParkingAreaDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link ParkingAreaDeleteServiceImpl} for deleting parking areas.
 */
@Service
@RequiredArgsConstructor
class ParkingAreaDeleteServiceImpl implements ParkingAreaDeleteService {


    private final ParkingAreaRepository parkingAreaRepository;

    /**
     * Deletes a parking area by its ID.
     *
     * @param parkingAreaId the ID of the parking area to delete
     */
    @Override
    public void deleteParkingAreaById(
            final String parkingAreaId
    ) {

        final ParkingAreaEntity parkingAreaEntityToBeDelete = parkingAreaRepository.findById(parkingAreaId)
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given parkingAreaId: " + parkingAreaId));

        parkingAreaRepository.delete(parkingAreaEntityToBeDelete);

    }

}