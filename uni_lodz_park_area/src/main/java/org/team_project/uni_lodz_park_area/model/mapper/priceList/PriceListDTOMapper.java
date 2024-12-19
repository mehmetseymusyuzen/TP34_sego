package org.team_project.uni_lodz_park_area.model.mapper.priceList;

import org.team_project.uni_lodz_park_area.model.dto.request.priceList.PriceListCreateRequest;
import org.team_project.uni_lodz_park_area.model.entity.PriceListEntity;

/**
 * Mapper class named {@link PriceListDTOMapper} to map {@link PriceListCreateRequest} to {@link PriceListEntity} for saving.
 */
public class PriceListDTOMapper {

    /**
     * Maps a {@link PriceListCreateRequest} object to a {@link PriceListEntity} object for saving.
     *
     * @param priceListCreateRequest The {@link PriceListCreateRequest} object to map.
     * @return The mapped {@link PriceListEntity} object.
     */
    public static PriceListEntity mapForSaving(
            final PriceListCreateRequest priceListCreateRequest
    ) {
        return PriceListEntity.builder()
                .name(priceListCreateRequest.getName())
                .build();
    }

}
