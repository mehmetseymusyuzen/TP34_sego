package org.team_project.uni_lodz_park_area.service.parking_area.impl;

import org.team_project.uni_lodz_park_area.exception.parking_area.DailyIncomeException;
import org.team_project.uni_lodz_park_area.exception.parking_area.InvalidDateException;
import org.team_project.uni_lodz_park_area.exception.parking_area.ParkingAreaNotFoundException;
import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.dto.response.parkingarea.ParkingAreaIncomeResponse;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.mapper.parking_area.ParkingAreaEntityToParkingAreaMapper;
import org.team_project.uni_lodz_park_area.repository.ParkingAreaRepository;
import org.team_project.uni_lodz_park_area.service.parking_area.ParkingAreaGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation class named {@link ParkingAreaGetServiceImpl} for getting parking areas.
 */
@Service
@RequiredArgsConstructor
class ParkingAreaGetServiceImpl implements ParkingAreaGetService {

    private final ParkingAreaRepository parkingAreaRepository;

    private final ParkingAreaEntityToParkingAreaMapper parkingAreaEntityToParkingAreaMapper =
            ParkingAreaEntityToParkingAreaMapper.initialize();


    /**
     * Retrieves a parking area by its ID.
     *
     * @param parkingAreaId the ID of the parking area to retrieve
     * @return the retrieved parking area
     */
    @Override
    public ParkingArea getParkingAreaById(final String parkingAreaId) {

        final ParkingAreaEntity existingParkingArea = parkingAreaRepository.findById(parkingAreaId)
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given parkingAreaId: " + parkingAreaId));

        return parkingAreaEntityToParkingAreaMapper.map(existingParkingArea);

    }

    /**
     * Retrieves a parking area by its name.
     *
     * @param name the name of the parking area to retrieve
     * @return the retrieved parking area
     */
    @Override
    public ParkingArea getParkingAreaByName(final String name) {

        final ParkingAreaEntity existingParkingArea = parkingAreaRepository.findByName(name)
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given name: " + name));

        return parkingAreaEntityToParkingAreaMapper.map(existingParkingArea);

    }

    /**
     * Retrieves daily income for a parking area on a given date.
     *
     * @param date         the date for which to retrieve income
     * @param parkingAreaId the ID of the parking area
     * @return the daily income of the parking area
     */
    @Override
    public ParkingAreaIncomeResponse getDailyIncome(final LocalDate date, final String parkingAreaId) {

        ParkingAreaEntity parkingAreaEntity = parkingAreaRepository
                .findById(parkingAreaId)
                .orElseThrow(ParkingAreaNotFoundException::new);

        isGivenDateAfterCurrentDate(date);

        BigDecimal calculatedIncome=parkingAreaRepository.calculateDailyIncome(date, parkingAreaId)
                .orElseThrow(DailyIncomeException::new);

        return ParkingAreaIncomeResponse.builder()
                .income(calculatedIncome)
                .name(parkingAreaEntity.getName())
                .build();
    }

    /**
     * Retrieves all parking areas.
     *
     * @return list of all parking areas
     */
    @Override
    public List<ParkingArea> getAllParkingAreas() {
        return parkingAreaRepository.findAll().stream()
                .map(parkingAreaEntityToParkingAreaMapper::map)
                .collect(Collectors.toList());
    }

    /**
     * Checks if the given date is after the current date.
     *
     * @param date the date to check
     * @throws InvalidDateException if the given date is after the current date
     */
    private void isGivenDateAfterCurrentDate(final LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw new InvalidDateException();
        }
    }

}