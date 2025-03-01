package org.team_project.uni_lodz_park_area.repository;

import org.team_project.uni_lodz_park_area.model.entity.ParkEntity;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.model.enums.ParkStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface named {@link ParkRepository} for managing park records.
 */
public interface ParkRepository extends JpaRepository<ParkEntity, String> {

    /**
     * Counts park records by parking area and park status.
     *
     * @param parkingArea the parking area
     * @param parkStatus  the park status
     * @return the count of park records
     */
    Integer countByParkingAreaEntityAndParkStatus(
            final ParkingAreaEntity parkingArea,
            final ParkStatus parkStatus
    );

    /**
     * Finds the latest park record by vehicle and park status.
     *
     * @param vehicle    the vehicle
     * @param parkStatus the park status
     * @return the latest park record
     */
    Optional<ParkEntity> findTopByVehicleEntityAndParkStatusOrderByCheckInDesc(
            final VehicleEntity vehicle,
            final ParkStatus parkStatus
    );

}
