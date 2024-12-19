package org.team_project.uni_lodz_park_area.model.dto.response.park;

import org.team_project.uni_lodz_park_area.common.model.BaseDomainModel;
import org.team_project.uni_lodz_park_area.model.dto.request.park.ParkCheckInRequest;
import org.team_project.uni_lodz_park_area.model.dto.response.vehicle.VehicleCheckInResponse;
import org.team_project.uni_lodz_park_area.model.enums.ParkStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * A response class named {@link ParkCheckInResponse} representing the check-in response.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ParkCheckInResponse extends BaseDomainModel {

    private String parkingAreaId;
    private VehicleCheckInResponse vehicleCheckInResponse;
    private ParkStatus parkStatus;
    private LocalDateTime checkIn;
}
