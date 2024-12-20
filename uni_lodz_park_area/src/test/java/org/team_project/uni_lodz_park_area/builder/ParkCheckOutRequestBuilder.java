package org.team_project.uni_lodz_park_area.builder;

import com.github.javafaker.Faker;
import org.team_project.uni_lodz_park_area.model.dto.request.park.ParkCheckOutRequest;
import org.team_project.uni_lodz_park_area.model.dto.request.vehicle.VehicleRequest;

public class ParkCheckOutRequestBuilder extends BaseBuilder<ParkCheckOutRequest> {

    public ParkCheckOutRequestBuilder() {
        super(ParkCheckOutRequest.class);
    }

    public ParkCheckOutRequestBuilder withAllFields() {
        final Faker faker = new Faker();
        return this
                .withParkingAreaId(faker.idNumber().toString())
                .withVehicleRequest(
                        new VehicleRequestBuilder()
                                .withValidFields()
                                .build()
                );
    }

    public ParkCheckOutRequestBuilder withParkingAreaId(
            final String parkingAreaId
    ) {
        data.setParkingAreaId(parkingAreaId);
        return this;
    }

    public ParkCheckOutRequestBuilder withVehicleRequest(
            final VehicleRequest vehicleRequest
    ) {
        data.setVehicleRequest(vehicleRequest);
        return this;
    }

    @Override
    public ParkCheckOutRequest build() {
        return super.build();
    }
}
