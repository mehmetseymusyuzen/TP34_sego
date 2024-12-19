package org.team_project.uni_lodz_park_area.service.park.impl;

import org.team_project.uni_lodz_park_area.exception.parkingarea.ParkingAreaCapacityException;
import org.team_project.uni_lodz_park_area.exception.parkingarea.ParkingAreaNotFoundException;
import org.team_project.uni_lodz_park_area.model.Park;
import org.team_project.uni_lodz_park_area.model.Vehicle;
import org.team_project.uni_lodz_park_area.model.dto.request.park.ParkCheckInRequest;
import org.team_project.uni_lodz_park_area.model.dto.request.park.ParkCheckOutRequest;
import org.team_project.uni_lodz_park_area.model.dto.response.park.ParkCheckInResponse;
import org.team_project.uni_lodz_park_area.model.dto.response.park.ParkCheckOutResponse;
import org.team_project.uni_lodz_park_area.model.entity.ParkEntity;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.entity.PriceListEntity;
import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.model.enums.ParkStatus;
import org.team_project.uni_lodz_park_area.model.mapper.park.*;
import org.team_project.uni_lodz_park_area.model.mapper.parking_area.ParkingAreaToParkingAreaEntityMapper;
import org.team_project.uni_lodz_park_area.model.mapper.vehicle.VehicleToVehicleEntityMapper;
import org.team_project.uni_lodz_park_area.repository.ParkRepository;
import org.team_project.uni_lodz_park_area.repository.ParkingAreaRepository;
import org.team_project.uni_lodz_park_area.service.park.ParkService;
import org.team_project.uni_lodz_park_area.service.vehicle.VehicleService;
import org.team_project.uni_lodz_park_area.utils.price.FeeCalculationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Service implementation class named {@link ParkServiceImpl} for managing park operations.
 */
@Service
@RequiredArgsConstructor
public class ParkServiceImpl implements ParkService {

    private final ParkRepository parkRepository;

    private final ParkingAreaRepository parkingAreaRepository;

    private final VehicleService vehicleService;

    private final ParkCheckInRequestToParkEntityMapper parkCheckInRequestToParkEntityMapper = ParkCheckInRequestToParkEntityMapper.initialize();

    private final ParkEntityToParkMapper parkEntityToParkMapper = ParkEntityToParkMapper.initialize();

    private final ParkToParkEntityMapper parkToParkEntityMapper = ParkToParkEntityMapper.initialize();

    private final ParkToParkCheckInResponseMapper parkToParkCheckInResponseMapper = ParkToParkCheckInResponseMapper.initialize();

    private final VehicleToVehicleEntityMapper vehicleToVehicleEntityMapper = VehicleToVehicleEntityMapper.initialize();

    private final ParkingAreaToParkingAreaEntityMapper parkingAreaToParkingAreaEntityMapper = ParkingAreaToParkingAreaEntityMapper.initialize();

    private final ParkEntityToParkCheckOutResponseMapper parkEntityToParkCheckOutResponseMapper = ParkEntityToParkCheckOutResponseMapper.initialize();

    /**
     * Checks in a vehicle to the parking area.
     *
     * @param userId            the ID of the user
     * @param parkCheckInRequest the check-in request
     * @return the check-in response
     */
    @Override
    @Transactional
    public ParkCheckInResponse checkIn(final String userId, final ParkCheckInRequest parkCheckInRequest) {

        final ParkingAreaEntity existingParkingEntityArea = parkingAreaRepository.findById(parkCheckInRequest.getParkingAreaId())
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given parkingAreaId: " + parkCheckInRequest.getParkingAreaId()));

        Vehicle vehicle = vehicleService.assignOrGet(userId, parkCheckInRequest.getVehicle());
        validateAvailableCapacity(existingParkingEntityArea);
        Park park = parkAvailableArea(parkCheckInRequest, vehicle, existingParkingEntityArea);
        return parkToParkCheckInResponseMapper.map(park);
    }

    /**
     * Checks out a vehicle from the parking area.
     *
     * @param userId              the ID of the user
     * @param parkCheckOutRequest the check-out request
     * @return the check-out response
     */
    @Override
    @Transactional
    public ParkCheckOutResponse checkOut(
            final String userId,
            final ParkCheckOutRequest parkCheckOutRequest
    ) {

        final ParkingAreaEntity existingParkingAreaEntity = parkingAreaRepository
                .findById(parkCheckOutRequest.getParkingAreaId())
                .orElseThrow(
                        () -> new ParkingAreaNotFoundException(
                                "With given Parking Area Id: " + parkCheckOutRequest.getParkingAreaId()
                        )
                );

        final VehicleEntity existingVehicle = vehicleService.findByLicensePlate(
                parkCheckOutRequest.getVehicleRequest().getLicensePlate()
        );

        ParkEntity parkEntity = parkRepository.findTopByVehicleEntityAndParkStatusOrderByCheckInDesc(
                existingVehicle,
                ParkStatus.FULL
        ).orElseThrow(
                () -> new ParkingAreaNotFoundException(
                        "With given Parking Area Id: " + parkCheckOutRequest.getParkingAreaId()
                )
        );

        parkEntity.setCheckOut(LocalDateTime.now());
        parkEntity.setParkStatus(ParkStatus.EMPTY);

        PriceListEntity priceListEntity = parkEntity.getParkingAreaEntity().getPriceListEntity();

        BigDecimal priceForTimeInterval = FeeCalculationUtil.findPriceForTimeInterval(priceListEntity, parkEntity);

        parkEntity.setTotalCost(priceForTimeInterval);

        parkRepository.save(parkEntity);

        return parkEntityToParkCheckOutResponseMapper.map(parkEntity);
    }

    /**
     * Counts the number of current parks in the parking area.
     *
     * @param parkingAreaEntity the parking area entity
     * @return the number of current parks
     */
    @Override
    public Integer countCurrentParks(ParkingAreaEntity parkingAreaEntity) {
        return parkRepository.countByParkingAreaEntityAndParkStatus(parkingAreaEntity, ParkStatus.EMPTY);
    }

    /**
     * Parks a vehicle in the available parking area.
     *
     * @param checkInRequest           the check-in request
     * @param vehicle                  the vehicle to be parked
     * @param existingParkingEntityArea the existing parking area entity
     * @return the parked vehicle
     */
    private Park parkAvailableArea(ParkCheckInRequest checkInRequest, Vehicle vehicle, ParkingAreaEntity existingParkingEntityArea) {
        ParkEntity parkEntity = parkCheckInRequestToParkEntityMapper.map(checkInRequest);
        parkEntity.setVehicleEntity(vehicleToVehicleEntityMapper.map(vehicle));
        parkEntity.setParkingAreaEntity(existingParkingEntityArea);
        parkEntity.setParkStatus(ParkStatus.FULL);
        parkEntity.setCheckIn(LocalDateTime.now());
        ParkEntity savedParkEntity = parkRepository.save(parkEntity);
        return parkEntityToParkMapper.map(savedParkEntity);
    }

    /**
     * Validates the available capacity in the parking area.
     *
     * @param parkingAreaEntity the parking area entity to check
     * @throws ParkingAreaCapacityException if the parking area capacity is full
     */
    private void validateAvailableCapacity(ParkingAreaEntity parkingAreaEntity) {
        Integer currentParks = countCurrentParks(parkingAreaEntity);
        if (parkingAreaEntity.getCapacity() <= currentParks) {
            throw new ParkingAreaCapacityException();
        }
    }

}
