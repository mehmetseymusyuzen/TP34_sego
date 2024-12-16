package org.team_project.uni_lodz_park_area.model.dto.response.parkingarea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * A response class named {@link ParkingAreaIncomeResponse} representing the parking area income response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingAreaIncomeResponse {


    private String name;
    private BigDecimal income;
}
