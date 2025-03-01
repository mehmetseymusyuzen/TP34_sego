package org.team_project.uni_lodz_park_area.builder;

import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.team_project.uni_lodz_park_area.security.model.enums.Role;
import org.team_project.uni_lodz_park_area.utils.RandomUtil;

import java.util.Collections;

public class UserEntityBuilder extends BaseBuilder<UserEntity> {

    public UserEntityBuilder() {
        super(UserEntity.class);
    }

    public UserEntityBuilder customer() {
        return this
                .withId("1L")
                .withFullName(RandomUtil.generateRandomString())
                .withUsername(RandomUtil.generateRandomString())
                .withEmail(RandomUtil.generateRandomString().concat("@parkingalot.com"))
                .withRole(Role.ROLE_DRIVER)
                .withVehicle(new VehicleEntityBuilder().withValidFields().build());
    }

    public UserEntityBuilder admin() {
        return this.customer()
                .withRole(Role.ROLE_ADMIN);
    }

    public UserEntityBuilder withId(String id) {
        data.setId(id);
        return this;
    }

    public UserEntityBuilder withFullName(String fullName) {
        data.setFullName(fullName);
        return this;
    }

    public UserEntityBuilder withUsername(String username) {
        data.setUsername(username);
        return this;
    }

    public UserEntityBuilder withEmail(String email) {
        data.setEmail(email);
        return this;
    }

    public UserEntityBuilder withRole(Role role) {
        data.setRole(role);
        return this;
    }

    public UserEntityBuilder withVehicle(VehicleEntity vehicle) {
        data.setVehicles(Collections.singletonList(vehicle));
        return this;
    }

}
