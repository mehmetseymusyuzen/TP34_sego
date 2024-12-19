package org.team_project.uni_lodz_park_area.model.dto.response.park;

import org.team_project.uni_lodz_park_area.common.model.BaseDomainModel;
import org.team_project.uni_lodz_park_area.model.dto.response.vehicle.VehicleCheckOutResponse;
import org.team_project.uni_lodz_park_area.model.enums.ParkStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A response class named {@link ParkCheckOutResponse} representing the check-out response.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParkCheckOutResponse extends BaseDomainModel {

    private String parkingAreaId;
    private String parkingAreaName;
    private VehicleCheckOutResponse vehicleCheckOutResponse;
    private ParkStatus parkStatus;
    private LocalDateTime checkOut;
    private BigDecimal totalCost;
}
