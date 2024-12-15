package org.team_project.uni_lodz_park_area.model.mapper;

import com.project.parkinglot.common.model.BaseDomainModel;
import com.project.parkinglot.model.enums.VehicleType;
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
    private List<com.project.parkinglot.model.Park> parkList;
    private User user;

}
