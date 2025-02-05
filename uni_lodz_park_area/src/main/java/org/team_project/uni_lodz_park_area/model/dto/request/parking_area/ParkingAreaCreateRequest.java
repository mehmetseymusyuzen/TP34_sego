package org.team_project.uni_lodz_park_area.model.dto.request.parking_area;

import org.team_project.uni_lodz_park_area.model.dto.request.priceList.PriceListCreateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A request class named {@link ParkingAreaCreateRequest} representing the update of a parking area.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingAreaCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 1000, message = "Capacity must be at most 1000")
    private Integer capacity;

    @NotBlank
    private String city;

    @Valid
    @NotNull
    private PriceListCreateRequest priceListRequest;

}
