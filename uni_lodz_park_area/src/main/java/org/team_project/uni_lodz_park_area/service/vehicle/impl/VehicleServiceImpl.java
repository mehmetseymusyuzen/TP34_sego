package org.team_project.uni_lodz_park_area.service.vehicle.impl;

import org.team_project.uni_lodz_park_area.exception.user.UserNotFoundException;
import org.team_project.uni_lodz_park_area.exception.vehicle.VehicleAlreadyExist;
import org.team_project.uni_lodz_park_area.exception.vehicle.VehicleNotFoundException;
import org.team_project.uni_lodz_park_area.model.Vehicle;
import org.team_project.uni_lodz_park_area.model.dto.request.vehicle.VehicleRequest;
import org.team_project.uni_lodz_park_area.model.dto.response.ParkDetailResponse;
import org.team_project.uni_lodz_park_area.model.dto.response.VehicleParkingDetailResponse;
import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.model.mapper.park.ParkEntityToParkDetailResponse;
import org.team_project.uni_lodz_park_area.model.mapper.vehicle.VehicleEntityToVehicleMapper;
import org.team_project.uni_lodz_park_area.model.mapper.vehicle.VehicleRequestToVehicleMapper;
import org.team_project.uni_lodz_park_area.model.mapper.vehicle.VehicleToVehicleEntityMapper;
import org.team_project.uni_lodz_park_area.repository.VehicleRepository;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.team_project.uni_lodz_park_area.service.auth.UserService;
import org.team_project.uni_lodz_park_area.service.vehicle.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation class named {@link VehicleServiceImpl} for managing vehicle operations.
 */
@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final UserService userService;

    private final VehicleToVehicleEntityMapper vehicleToVehicleEntityMapper =
            VehicleToVehicleEntityMapper.initialize();

    private final ParkEntityToParkDetailResponse parkEntityToParkDetailResponse =
            ParkEntityToParkDetailResponse.initialize();

    private final VehicleRequestToVehicleMapper vehicleRequestToVehicleMapper=
            VehicleRequestToVehicleMapper.initialize();

    private final VehicleEntityToVehicleMapper vehicleEntityToVehicleMapper =
            VehicleEntityToVehicleMapper.initialize();

    /**
     * Assigns a vehicle to a user.
     *
     * @param id             the ID of the user
     * @param vehicleRequest the vehicle details
     * @return the assigned vehicle
     */
    @Override
    @Transactional
    public Vehicle assignVehicleToUser(final String id, final VehicleRequest vehicleRequest) {

        final UserEntity userEntity = userService.findById(id)
                .orElseThrow( ()-> new UserNotFoundException("Cant find user given id"));

        final Vehicle vehicle = vehicleRequestToVehicleMapper.map(vehicleRequest);

        final VehicleEntity vehicleEntity = assignUserToVehicle(userEntity, vehicle);

        return vehicleEntityToVehicleMapper.map(vehicleEntity);

    }

    /**
     * Checks if a vehicle with the given license plate already exists.
     *
     * @param vehicle the vehicle to check
     * @throws VehicleAlreadyExist if a vehicle with the same license plate already exists
     */
    private void existByLicensePlate (final Vehicle vehicle){

        if (Boolean.TRUE.equals(vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate()))){
            throw new VehicleAlreadyExist();
        }

    }

    /**
     * Assigns a user to a vehicle and saves the vehicle entity.
     *
     * @param userEntity the user entity to assign to the vehicle
     * @param vehicle    the vehicle to assign the user to
     * @return the persisted vehicle entity with the assigned user
     * @throws VehicleAlreadyExist if a vehicle with the same license plate already exists
     */
    private VehicleEntity assignUserToVehicle(final UserEntity userEntity, final Vehicle vehicle){

        existByLicensePlate(vehicle);

        final VehicleEntity vehicleEntityToBePersist = vehicleToVehicleEntityMapper
                .map(vehicle);

        vehicleEntityToBePersist.setUserEntity(userEntity);

        vehicleRepository.save(vehicleEntityToBePersist);

        return vehicleEntityToBePersist;

    }

    /**
     * Assigns a vehicle to a user if not assigned already; otherwise, retrieves the assigned vehicle.
     *
     * @param userId         the ID of the user
     * @param vehicleRequest the vehicle details
     * @return the assigned or retrieved vehicle
     */
    @Override
    @Transactional
    public Vehicle assignOrGet(final String userId, final VehicleRequest vehicleRequest) {
        final Optional<VehicleEntity> existingVehicle = vehicleRepository.findByLicensePlate(vehicleRequest.getLicensePlate());
        final VehicleEntity vehicleEntity = existingVehicle.orElseGet(() -> {
            Vehicle assignedVehicleEntity = assignVehicleToUser(userId, vehicleRequest);
            return vehicleToVehicleEntityMapper.map(assignedVehicleEntity);
        });
        return vehicleEntityToVehicleMapper.map(vehicleEntity);
    }

    /**
     * Retrieves a vehicle entity by its license plate.
     *
     * @param licensePlate the license plate of the vehicle
     * @return the vehicle entity
     */
    @Override
    public VehicleEntity findByLicensePlate(final String licensePlate) {
        return vehicleRepository
                .findByLicensePlate(licensePlate)
                .orElseThrow(VehicleNotFoundException::new);
    }

    /**
     * Retrieves parking details of a vehicle by its license plate.
     *
     * @param licensePlate the license plate of the vehicle
     * @return the parking details of the vehicle
     */
    @Override
    public VehicleParkingDetailResponse getParkingDetails(final String licensePlate) {
        final VehicleEntity vehicleEntity = vehicleRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new VehicleNotFoundException(licensePlate));

        // En son park kaydını bul
        Optional<ParkDetailResponse> lastParkDetail = vehicleEntity.getParkEntities().stream()
                .filter(park -> park.getCheckOut() == null) // Check-out yapılmamış park kaydı
                .map(parkEntityToParkDetailResponse::map)
                .findFirst();

        return VehicleParkingDetailResponse.builder()
                .licensePlate(vehicleEntity.getLicensePlate())
                .isParked(lastParkDetail.isPresent())
                .parkingAreaName(lastParkDetail.map(ParkDetailResponse::getParkingAreaName).orElse(null))
                .parkingAreaId(lastParkDetail.map(ParkDetailResponse::getParkingAreaId).orElse(null))
                .checkInTime(lastParkDetail.map(ParkDetailResponse::getCheckInDate).orElse(null))
                .vehicleType(vehicleEntity.getVehicleType())
                .build();
    }

    @Override
    public List<Vehicle> getUserVehicles(final String userId) {
        final UserEntity userEntity = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Cant find user given id"));

        return vehicleRepository.findByUserEntity(userEntity)
                .stream()
                .map(vehicleEntityToVehicleMapper::map)
                .collect(Collectors.toList());
    }

}
