package org.team_project.uni_lodz_park_area.model.dto.response.park;

import com.project.parkinglot.common.model.BaseDomainModel;
import com.project.parkinglot.model.dto.response.vehicle.VehicleCheckOutResponse;
import com.project.parkinglot.model.enums.ParkStatus;
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
