package org.team_project.uni_lodz_park_area.model.dto.response;

import org.team_project.uni_lodz_park_area.common.model.BaseDomainModel;
import org.team_project.uni_lodz_park_area.model.dto.response.vehicle.VehicleCheckOutResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A response class named {@link ParkDetailResponse} representing the park detail response.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParkDetailResponse extends BaseDomainModel {

    private String parkingAreaName;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private BigDecimal totalCost;
    private String parkingAreaId;

}
