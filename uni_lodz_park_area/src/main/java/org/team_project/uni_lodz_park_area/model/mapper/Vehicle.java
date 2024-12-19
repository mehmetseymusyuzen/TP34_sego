package org.team_project.uni_lodz_park_area.model.mapper;

import org.team_project.uni_lodz_park_area.common.model.BaseDomainModel;
import org.team_project.uni_lodz_park_area.model.Park;
import org.team_project.uni_lodz_park_area.model.User;
import org.team_project.uni_lodz_park_area.model.enums.VehicleType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents a vehicle domain model object named {@link Vehicle}.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends BaseDomainModel {

    private String id;
    private String licensePlate;
    private VehicleType vehicleType;
    private List<Park> parkList;
    private User user;

}
