package org.team_project.uni_lodz_park_area.model.mapper.price;

import org.team_project.uni_lodz_park_area.model.dto.request.price.PriceCreateRequest;
import org.team_project.uni_lodz_park_area.model.entity.PriceEntity;

/**
 * Mapper class named {@link PriceDTOMapper} to map {@link PriceCreateRequest} to {@link PriceEntity} for saving.
 */
public class PriceDTOMapper {

    /**
     * Maps a {@link PriceCreateRequest} object to a {@link PriceEntity} object for saving.
     *
     * @param priceCreateRequest The {@link PriceCreateRequest} object to map.
     * @return The mapped {@link PriceEntity} object.
     */
    public static PriceEntity mapForSaving(
            final PriceCreateRequest priceCreateRequest
    ) {
        return PriceEntity.builder()
                .lowerBound(priceCreateRequest.getLowerBound())
                .upperBound(priceCreateRequest.getUpperBound())
                .cost(priceCreateRequest.getCost())
                .build();
    }

}
