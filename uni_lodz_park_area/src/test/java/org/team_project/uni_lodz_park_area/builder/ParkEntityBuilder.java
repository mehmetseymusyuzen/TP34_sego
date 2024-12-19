package org.team_project.uni_lodz_park_area.builder;

import org.team_project.uni_lodz_park_area.model.entity.ParkEntity;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.model.enums.ParkStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParkEntityBuilder extends BaseBuilder<ParkEntity> {

    public ParkEntityBuilder() {
        super(ParkEntity.class);
    }

    public ParkEntityBuilder withValidFields() {
        return this
                .withId(UUID.randomUUID().toString())
                .withParkingAreaEntity(new ParkingAreaEntityBuilder().withValidFields().build())
                .withVehicleEntity(new VehicleEntityBuilder().withValidFields().build())
                .withCheckIn()
                .withCheckOut()
                .withTotalCost(BigDecimal.valueOf(10))
                .withParkStatus(ParkStatus.FULL);
    }

    public ParkEntityBuilder withId(final String id) {
        data.setId(id);
        return this;
    }

    public ParkEntityBuilder withVehicleEntity(final VehicleEntity vehicleEntity) {
        data.setVehicleEntity(vehicleEntity);
        return this;
    }

    public ParkEntityBuilder withParkingAreaEntity(final ParkingAreaEntity parkingAreaEntity) {
        data.setParkingAreaEntity(parkingAreaEntity);
        return this;
    }

    public ParkEntityBuilder withParkStatus(final ParkStatus parkStatus) {
        data.setParkStatus(parkStatus);
        return this;
    }

    public ParkEntityBuilder withCheckIn() {
        data.setCheckIn(LocalDateTime.now());
        return this;
    }

    public ParkEntityBuilder withCheckOut() {
        data.setCheckOut(LocalDateTime.now());
        return this;
    }

    public ParkEntityBuilder withTotalCost(final BigDecimal totalCost) {
        data.setTotalCost(totalCost);
        return this;
    }

}

