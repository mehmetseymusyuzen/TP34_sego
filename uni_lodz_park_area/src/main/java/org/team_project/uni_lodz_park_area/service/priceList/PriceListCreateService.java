package org.team_project.uni_lodz_park_area.service.priceList;

import org.team_project.uni_lodz_park_area.model.PriceList;
import org.team_project.uni_lodz_park_area.model.dto.request.priceList.PriceListCreateRequest;

/**
 * Service interface named {@link PriceListCreateService} for creating a price list.
 */
public interface PriceListCreateService {

    /**
     * Creates a new price list.
     *
     * @param priceListCreateRequest the request containing the price list details
     * @return the created price list
     */
    PriceList createPriceList(
            final PriceListCreateRequest priceListCreateRequest
    );

}
