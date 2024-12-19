package org.team_project.uni_lodz_park_area.builder;

import com.github.javafaker.Faker;
import org.team_project.uni_lodz_park_area.model.Park;
import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.PriceList;

import java.util.List;
import java.util.UUID;

public class ParkingAreaBuilder extends BaseBuilder<ParkingArea> {

    public ParkingAreaBuilder() {
        super(ParkingArea.class);
    }

    public ParkingAreaBuilder withValidFields() {

        final Faker faker = new Faker();

        return this
                .withId(UUID.randomUUID().toString())
                .withName(faker.name().name())
                .withLocation(faker.address().fullAddress())
                .withCapacity(faker.number().numberBetween(1, 100))
                .withCity(faker.address().city())
                .withPriceList(new PriceListBuilder().withValidFields().build())
                .withParkList(List.of(new ParkBuilder().withValidFields().build()));
    }

    public ParkingAreaBuilder withId(final String id) {
        data.setId(id);
        return this;
    }

    public ParkingAreaBuilder withName(final String name) {
        data.setName(name);
        return this;
    }

    public ParkingAreaBuilder withLocation(final String location) {
        data.setLocation(location);
        return this;
    }

    public ParkingAreaBuilder withCapacity(final Integer capacity) {
        data.setCapacity(capacity);
        return this;
    }

    public ParkingAreaBuilder withCity(final String city) {
        data.setCity(city);
        return this;
    }

    public ParkingAreaBuilder withPriceList(final PriceList priceList) {
        data.setPriceList(priceList);
        return this;
    }

    public ParkingAreaBuilder withParkList(final List<Park> parkList) {
        data.setParkList(parkList);
        return this;
    }

}
