package org.team_project.uni_lodz_park_area.model.dto.response.vehicle;

import com.project.parkinglot.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A response class named {@link VehicleCheckInResponse} representing the vehicle check-in response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleCheckInResponse {

    private String licensePlate;
    private VehicleType vehicleType;
}
