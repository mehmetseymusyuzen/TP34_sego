package org.team_project.uni_lodz_park_area.model;

import org.team_project.uni_lodz_park_area.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
