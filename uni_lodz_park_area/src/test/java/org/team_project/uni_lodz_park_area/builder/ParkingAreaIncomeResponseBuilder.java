package org.team_project.uni_lodz_park_area.builder;

import com.github.javafaker.Faker;
import org.team_project.uni_lodz_park_area.model.dto.response.parkingarea.ParkingAreaIncomeResponse;

import java.math.BigDecimal;

public class ParkingAreaIncomeResponseBuilder extends BaseBuilder<ParkingAreaIncomeResponse>{

    public ParkingAreaIncomeResponseBuilder() {super(ParkingAreaIncomeResponse.class);}

    public ParkingAreaIncomeResponseBuilder withValidFields(){
        final Faker faker = new Faker();
        return this
                .withName(faker.name().name())
                .withIncome(new BigDecimal(faker.number().randomNumber()));

    }

    public ParkingAreaIncomeResponseBuilder withName(
            final String name
    ){
        data.setName(name);
        return this;
    }

    public ParkingAreaIncomeResponseBuilder withIncome(
            final BigDecimal income
    ){
        data.setIncome(income);
        return this;
    }

    @Override
    public ParkingAreaIncomeResponse build(){return super.build();}
}
