package org.team_project.uni_lodz_park_area.service.vehicle;

import org.team_project.uni_lodz_park_area.model.Vehicle;
import org.team_project.uni_lodz_park_area.model.dto.request.vehicle.VehicleRequest;
import org.team_project.uni_lodz_park_area.model.dto.response.VehicleParkingDetailResponse;
import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;

import java.util.List;

/**
 * Service interface named {@link VehicleService} for managing vehicles.
 */
public interface VehicleService {

    /**
     * Assigns a vehicle to a user.
     *
     * @param id             the ID of the user
     * @param vehicleRequest the vehicle details
     * @return the assigned vehicle
     */
    Vehicle assignVehicleToUser (
            String id,
            VehicleRequest vehicleRequest
    );

    /**
     * Assigns a vehicle to a user if not assigned already; otherwise, retrieves the assigned vehicle.
     *
     * @param userId         the ID of the user
     * @param vehicleRequest the vehicle details
     * @return the assigned or retrieved vehicle
     */
    Vehicle assignOrGet(final String userId, final VehicleRequest vehicleRequest);

    /**
     * Retrieves a vehicle entity by its license plate.
     *
     * @param licensePlate the license plate of the vehicle
     * @return the vehicle entity
     */
    VehicleEntity findByLicensePlate(final String licensePlate);

    /**
     * Retrieves parking details of a vehicle by its license plate.
     *
     * @param licensePlate the license plate of the vehicle
     * @return the parking details of the vehicle
     */
    VehicleParkingDetailResponse getParkingDetails(final String licensePlate);

    /**
     * Retrieves all vehicles assigned to a specific user.
     *
     * @param userId the ID of the user
     * @return list of vehicles assigned to the user
     */
    List<Vehicle> getUserVehicles(String userId);

}
