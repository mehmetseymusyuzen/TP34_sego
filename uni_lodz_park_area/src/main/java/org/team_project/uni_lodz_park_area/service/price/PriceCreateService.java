package org.team_project.uni_lodz_park_area.service.price;

import org.team_project.uni_lodz_park_area.model.Price;
import org.team_project.uni_lodz_park_area.model.PriceList;
import org.team_project.uni_lodz_park_area.model.dto.request.price.PriceCreateRequest;

/**
 * Service interface named {@link PriceCreateService} for creating a price.
 */
public interface PriceCreateService {

    /**
     * Creates a new price.
     *
     * @param priceList the price list to which the price belongs
     * @param priceCreateRequest the request containing the price details
     * @return the created price
     */
    Price createPrice(
            final PriceList priceList,
            final PriceCreateRequest priceCreateRequest
    );

}
