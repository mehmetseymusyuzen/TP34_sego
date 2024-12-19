package org.team_project.uni_lodz_park_area.service.priceList.impl;

import org.team_project.uni_lodz_park_area.model.Price;
import org.team_project.uni_lodz_park_area.model.PriceList;
import org.team_project.uni_lodz_park_area.model.dto.request.priceList.PriceListCreateRequest;
import org.team_project.uni_lodz_park_area.model.entity.PriceListEntity;
import org.team_project.uni_lodz_park_area.model.mapper.priceList.PriceListDTOMapper;
import org.team_project.uni_lodz_park_area.model.mapper.priceList.PriceListMapper;
import org.team_project.uni_lodz_park_area.repository.PriceListRepository;
import org.team_project.uni_lodz_park_area.service.price.PriceCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.team_project.uni_lodz_park_area.service.priceList.PriceListCreateService;

import java.util.List;
import java.util.Objects;

/**
 * Service implementation class named {@link PriceListCreateServiceImpl} for creating price list.
 */
@Service
@RequiredArgsConstructor
public class PriceListCreateServiceImpl implements PriceListCreateService {

    private final PriceListRepository priceListRepository;
    private final PriceCreateService priceCreateService;

    /**
     * Creates a new price list.
     *
     * @param priceListCreateRequest the request containing the price list details
     * @return the created price list
     */
    @Override
    public PriceList createPriceList(
            final PriceListCreateRequest priceListCreateRequest
    ) {
        PriceListEntity priceListEntityToBeSave = PriceListDTOMapper
                .mapForSaving(priceListCreateRequest);

        priceListRepository.save(priceListEntityToBeSave);

        final PriceList priceListDomainModel = PriceListMapper
                .toDomainModel(priceListEntityToBeSave);

        if (Objects.isNull(priceListCreateRequest.getPrices())) {
            return priceListDomainModel;
        }

        final List<Price> createdPrices = priceListCreateRequest
                .getPrices()
                .stream()
                .map(
                        priceCreateRequest ->
                                priceCreateService
                                        .createPrice(
                                                priceListDomainModel,
                                                priceCreateRequest
                                        )
                )
                .toList();

        priceListDomainModel.setPrices(createdPrices);

        return priceListDomainModel;
    }

}
