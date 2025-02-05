package org.team_project.uni_lodz_park_area.model.dto.request.parking_area;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A request class named {@link ParkingAreaUpdateRequest} representing the creation of a parking area.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParkingAreaUpdateRequest {

    @NotNull
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 1000, message = "Capacity must be at most 1000")
    private Integer capacity;

}
