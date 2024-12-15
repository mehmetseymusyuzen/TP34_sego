package org.team_project.uni_lodz_park_area.service.parking_area.implementation;

import org.team_project.uni_lodz_park_area.exception.parking_area.ParkingAreaAlreadyExistException;
import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.PriceList;
import org.team_project.uni_lodz_park_area.model.dto.request.parking_area.ParkingAreaCreateRequest;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.mapper.parking_area.ParkingAreaCreateRequestToParkingAreaEntityMapper;
import org.team_project.uni_lodz_park_area.model.mapper.parking_area.ParkingAreaEntityToParkingAreaMapper;
import org.team_project.uni_lodz_park_area.model.mapper.priceList.PriceListMapper;
import org.team_project.uni_lodz_park_area.repository.ParkingAreaRepository;
import org.team_project.uni_lodz_park_area.service.parking_area.ParkingAreaCreateService;
import org.team_project.uni_lodz_park_area.service.priceList.PriceListCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service implementation class named {@link ParkingAreaCreateServiceImpl} for creating parking areas.
 */
@Service
@RequiredArgsConstructor
class ParkingAreaCreateServiceImpl implements ParkingAreaCreateService {

    private final ParkingAreaRepository parkingAreaRepository;
    private final PriceListCreateService priceListCreateService;

    private final ParkingAreaCreateRequestToParkingAreaEntityMapper parkingAreaCreateRequestToParkingAreaEntityMapper =
            ParkingAreaCreateRequestToParkingAreaEntityMapper.initialize();

    private final ParkingAreaEntityToParkingAreaMapper parkingAreaEntityToParkingAreaMapper =
            ParkingAreaEntityToParkingAreaMapper.initialize();

    /**
     * Creates a new parking area.
     *
     * @param parkingAreaCreateRequest the request containing details for creating the parking area
     * @return the created parking area
     */
    @Override
    public ParkingArea createParkingArea(
            final ParkingAreaCreateRequest parkingAreaCreateRequest
    ) {
        this.checkParkingAreaNameAndLocationUniqueness(
                parkingAreaCreateRequest.getName(),
                parkingAreaCreateRequest.getLocation()
        );

        ParkingAreaEntity parkingAreaToBeCreate = parkingAreaCreateRequestToParkingAreaEntityMapper
                .map(parkingAreaCreateRequest);

        parkingAreaRepository.save(parkingAreaToBeCreate);

        if (Objects.nonNull(parkingAreaCreateRequest.getPriceListRequest())) {
            final PriceList createdPriceListForParkingArea = priceListCreateService
                    .createPriceList(parkingAreaCreateRequest.getPriceListRequest());

            parkingAreaToBeCreate.setPriceListEntity(PriceListMapper.toEntity(createdPriceListForParkingArea));
        }

        return parkingAreaEntityToParkingAreaMapper
                .map(parkingAreaToBeCreate);
    }

    /**
     * Checks if a parking area with the given name and location already exists.
     *
     * @param name the name of the parking area to check
     * @param location the location of the parking area to check
     * @throws ParkingAreaAlreadyExistException if a parking area with the same name and location already exists
     */
    private void checkParkingAreaNameAndLocationUniqueness(
            final String name,
            final String location
    ) {
        if (Boolean.TRUE.equals(parkingAreaRepository
                .existsParkingAreaEntitiesByNameAndLocation(name, location))) {
            throw new ParkingAreaAlreadyExistException();
        }
    }

}