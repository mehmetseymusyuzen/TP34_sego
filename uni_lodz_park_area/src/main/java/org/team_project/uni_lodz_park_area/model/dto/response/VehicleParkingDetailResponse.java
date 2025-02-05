package org.team_project.uni_lodz_park_area.model.dto.response;

import org.team_project.uni_lodz_park_area.common.model.BaseDomainModel;
import org.team_project.uni_lodz_park_area.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * A response class named {@link VehicleParkingDetailResponse} representing the vehicle parking detail response.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class VehicleParkingDetailResponse extends BaseDomainModel {

    private String licensePlate;
    private boolean isParked;
    private String parkingAreaName;
    private String parkingAreaId;
    private LocalDateTime checkInTime;
    private VehicleType vehicleType;
}
