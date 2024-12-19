package org.team_project.uni_lodz_park_area.builder;

import org.team_project.uni_lodz_park_area.model.User;
import org.team_project.uni_lodz_park_area.model.Vehicle;
import org.team_project.uni_lodz_park_area.security.model.enums.Role;
import org.team_project.uni_lodz_park_area.utils.RandomUtil;

import java.util.ArrayList;

public class UserBuilder extends BaseBuilder<User> {

    public UserBuilder() {
        super(User.class);
        data.setVehicleList(new ArrayList<>());
    }

    public UserBuilder customer() {
        return this
                .withId("1L")
                .withFullName(RandomUtil.generateRandomString())
                .withUsername(RandomUtil.generateRandomString())
                .withEmail(RandomUtil.generateRandomString().concat("@parkingalot.com"))
                .withRole(Role.ROLE_DRIVER)
                .withVehicle(new VehicleBuilder().withValidFields().build());
    }

    public UserBuilder admin() {
        return this.customer()
                .withRole(Role.ROLE_ADMIN);
    }

    public UserBuilder withId(String id) {
        data.setId(id);
        return this;
    }

    public UserBuilder withFullName(String fullName) {
        data.setFullName(fullName);
        return this;
    }

    public UserBuilder withUsername(String username) {
        data.setUsername(username);
        return this;
    }

    public UserBuilder withEmail(String email) {
        data.setEmail(email);
        return this;
    }

    public UserBuilder withRole(Role role) {
        data.setRole(role);
        return this;
    }

    public UserBuilder withVehicle(Vehicle vehicle) {
        data.getVehicleList().add(vehicle);
        return this;
    }
}
