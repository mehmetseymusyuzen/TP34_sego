package org.team_project.uni_lodz_park_area.model.dto.request.park;

import org.team_project.uni_lodz_park_area.model.dto.request.vehicle.VehicleRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * A request class named {@link ParkCheckOutRequest} representing the check-out request for a vehicle from a parking area.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkCheckOutRequest {

    @NotNull(message = "Parking area id must be required")
    private String parkingAreaId;

    @NotNull(message = "Vehicle must be required")
    @Valid
    private VehicleRequest vehicleRequest;

}
