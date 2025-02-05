package org.team_project.uni_lodz_park_area.repository;

import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface named {@link VehicleRepository} for managing vehicles.
 */
public interface VehicleRepository extends JpaRepository<VehicleEntity, String> {

    /**
     * Checks if a vehicle exists by license plate.
     *
     * @param licensePlate the license plate of the vehicle
     * @return true if the vehicle exists, false otherwise
     */
    Boolean existsByLicensePlate(
            final String licensePlate
    );

    /**
     * Finds a vehicle by license plate.
     *
     * @param licensePlate the license plate of the vehicle
     * @return the vehicle if found, otherwise empty
     */
    Optional<VehicleEntity> findByLicensePlate(String licensePlate);

    /**
     * Finds all vehicles assigned to a specific user.
     *
     * @param userEntity the user entity
     * @return list of vehicles assigned to the user
     */
    List<VehicleEntity> findByUserEntity(UserEntity userEntity);

}
