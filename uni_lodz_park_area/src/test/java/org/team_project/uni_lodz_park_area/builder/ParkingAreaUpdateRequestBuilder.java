package org.team_project.uni_lodz_park_area.builder;

import com.github.javafaker.Faker;
import org.team_project.uni_lodz_park_area.model.dto.request.parking_area.ParkingAreaUpdateRequest;

public class ParkingAreaUpdateRequestBuilder extends BaseBuilder<ParkingAreaUpdateRequest>{

    public ParkingAreaUpdateRequestBuilder() {super(ParkingAreaUpdateRequest.class);}

    public ParkingAreaUpdateRequestBuilder withValidFields(){
        final Faker faker = new Faker();
        return this
                .withCapacity(faker.number().numberBetween(1,23));
    }

    public ParkingAreaUpdateRequestBuilder withCapacity(
            final Integer capacity
    ){
        data.setCapacity(capacity);
        return this;
    }

    @Override
    public ParkingAreaUpdateRequest build(){return super.build();}

}
