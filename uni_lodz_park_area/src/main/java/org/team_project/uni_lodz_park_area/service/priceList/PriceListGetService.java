package org.team_project.uni_lodz_park_area.service.priceList;

import org.team_project.uni_lodz_park_area.model.PriceList;

/**
 * Service interface named {@link PriceListGetService} for retrieving price lists.
 */
public interface PriceListGetService {

    /**
     * Retrieves a price list by parking area ID.
     *
     * @param parkingAreaId the ID of the parking area
     * @return the price list associated with the parking area
     */
    PriceList getPriceListByParkingAreaId(final String parkingAreaId);
} 