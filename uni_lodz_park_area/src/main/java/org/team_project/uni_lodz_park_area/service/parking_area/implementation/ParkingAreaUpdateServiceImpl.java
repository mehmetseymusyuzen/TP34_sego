package org.team_project.uni_lodz_park_area.service.parking_area.implementation;

import org.team_project.uni_lodz_park_area.exception.parkingarea.ParkingAreaNotFoundException;
import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.dto.request.parking_area.ParkingAreaUpdateRequest;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.mapper.parking_area.ParkingAreaEntityToParkingAreaMapper;
import org.team_project.uni_lodz_park_area.repository.ParkingAreaRepository;
import org.team_project.uni_lodz_park_area.service.parking_area.ParkingAreaUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link ParkingAreaUpdateServiceImpl} for updating parking areas.
 */
@Service
@RequiredArgsConstructor
class ParkingAreaUpdateServiceImpl implements ParkingAreaUpdateService {

    private final ParkingAreaRepository parkingAreaRepository;

    private final ParkingAreaEntityToParkingAreaMapper parkingAreaEntityToParkingArea =
            ParkingAreaEntityToParkingAreaMapper.initialize();

    /**
     * Updates the capacity of a parking area.
     *
     * @param parkingAreaId           the ID of the parking area to update
     * @param parkingAreaUpdateRequest the request containing the new capacity
     * @return the updated parking area
     */
    @Override
    public ParkingArea parkingAreaUpdateByCapacity(
            final String parkingAreaId,
            final ParkingAreaUpdateRequest parkingAreaUpdateRequest
    ) {

        final ParkingAreaEntity parkingAreaEntityToBeUpdate = parkingAreaRepository
                .findById(parkingAreaId)
                .orElseThrow(() -> new ParkingAreaNotFoundException("ParkingArea not found given id" + parkingAreaId));

        parkingAreaEntityToBeUpdate.setCapacity(parkingAreaUpdateRequest.getCapacity());

        parkingAreaRepository.save(parkingAreaEntityToBeUpdate);

        return parkingAreaEntityToParkingArea.map(parkingAreaEntityToBeUpdate);

    }

}