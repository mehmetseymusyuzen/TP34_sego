package org.team_project.uni_lodz_park_area.service.priceList.impl;

import org.team_project.uni_lodz_park_area.exception.parking_area.ParkingAreaNotFoundException;
import org.team_project.uni_lodz_park_area.exception.pricelist.PriceListNotFoundException;
import org.team_project.uni_lodz_park_area.model.PriceList;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.entity.PriceListEntity;
import org.team_project.uni_lodz_park_area.model.mapper.priceList.PriceListMapper;
import org.team_project.uni_lodz_park_area.repository.ParkingAreaRepository;
import org.team_project.uni_lodz_park_area.service.priceList.PriceListGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link PriceListGetServiceImpl} for retrieving price lists.
 */
@Service
@RequiredArgsConstructor
class PriceListGetServiceImpl implements PriceListGetService {

    private final ParkingAreaRepository parkingAreaRepository;

    /**
     * Retrieves a price list by parking area ID.
     *
     * @param parkingAreaId the ID of the parking area
     * @return the price list associated with the parking area
     * @throws ParkingAreaNotFoundException if the parking area is not found
     * @throws PriceListNotFoundException if the price list is not found
     */
    @Override
    public PriceList getPriceListByParkingAreaId(final String parkingAreaId) {
        final ParkingAreaEntity parkingArea = parkingAreaRepository.findById(parkingAreaId)
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given parkingAreaId: " + parkingAreaId));

        final PriceListEntity priceList = parkingArea.getPriceListEntity();
        if (priceList == null) {
            throw new PriceListNotFoundException("No price list found for parking area: " + parkingAreaId);
        }

        return PriceListMapper.toDomainModel(priceList);
    }
} 