package org.team_project.uni_lodz_park_area.model;

import org.team_project.uni_lodz_park_area.common.model.BaseDomainModel;
import org.team_project.uni_lodz_park_area.security.model.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents a user domain model object named {@link User}.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDomainModel {

    private String id;
    private String fullName;
    private String username;
    private String email;
    private Role role;
    private List<Vehicle> vehicleList;

}
