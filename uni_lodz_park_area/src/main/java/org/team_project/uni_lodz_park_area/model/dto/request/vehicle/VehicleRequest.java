package org.team_project.uni_lodz_park_area.model.dto.request.vehicle;

import org.team_project.uni_lodz_park_area.model.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A request class named {@link VehicleRequest} representing licensePlate and vehicleType.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleRequest {

    @NotBlank
    @Size(max = 10)
    private String licensePlate;

    private VehicleType vehicleType;

}
