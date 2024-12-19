package org.team_project.uni_lodz_park_area.service.price.impl;

import org.team_project.uni_lodz_park_area.model.Price;
import org.team_project.uni_lodz_park_area.model.PriceList;
import org.team_project.uni_lodz_park_area.model.dto.request.price.PriceCreateRequest;
import org.team_project.uni_lodz_park_area.model.entity.PriceEntity;
import org.team_project.uni_lodz_park_area.model.mapper.price.PriceDTOMapper;
import org.team_project.uni_lodz_park_area.model.mapper.price.PriceMapper;
import org.team_project.uni_lodz_park_area.model.mapper.priceList.PriceListMapper;
import org.team_project.uni_lodz_park_area.repository.PriceRepository;
import org.team_project.uni_lodz_park_area.service.parking_area.ParkingAreaCreateService;
import org.team_project.uni_lodz_park_area.service.price.PriceCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link PriceCreateServiceImpl} for creating price.
 */
@Service
@RequiredArgsConstructor
public class PriceCreateServiceImpl implements PriceCreateService {

    private final PriceRepository priceRepository;

    /**
     * Creates a new price.
     *
     * @param priceList the price list to which the price belongs
     * @param priceCreateRequest the request containing the price details
     * @return the created price
     */
    @Override
    public Price createPrice(
            final PriceList priceList,
            final PriceCreateRequest priceCreateRequest
    ) {
        PriceEntity priceEntityToBeSave = PriceDTOMapper
                .mapForSaving(priceCreateRequest);

        priceEntityToBeSave.setPriceListEntity(
                PriceListMapper.toEntity(priceList)
        );

        priceRepository.save(priceEntityToBeSave);

        return PriceMapper
                .toDomainModel(priceEntityToBeSave);
    }

}
