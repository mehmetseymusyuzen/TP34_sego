package org.team_project.uni_lodz_park_area.builder;

import org.team_project.uni_lodz_park_area.model.Park;
import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.Vehicle;
import org.team_project.uni_lodz_park_area.model.enums.ParkStatus;

import java.util.UUID;

public class ParkBuilder extends BaseBuilder<Park> {

    public ParkBuilder() {
        super(Park.class);
    }

    public ParkBuilder withValidFields() {
        return this
                .withId(UUID.randomUUID().toString())
                .withVehicle(new VehicleBuilder().withValidFields().build())
                .withParkStatus(ParkStatus.FULL);
    }

    public ParkBuilder withId(final String id) {
        data.setId(id);
        return this;
    }

    public ParkBuilder withVehicle(final Vehicle vehicle) {
        data.setVehicle(vehicle);
        return this;
    }

    public ParkBuilder withParkingArea(final ParkingArea parkingArea) {
        data.setParkingArea(parkingArea);
        return this;
    }

    public ParkBuilder withParkStatus(final ParkStatus parkStatus) {
        data.setParkStatus(parkStatus);
        return this;
    }
}
