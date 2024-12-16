package org.team_project.uni_lodz_park_area.utils.price;

import java.math.BigDecimal;

/**
 * Defines a strategy named {@link FeeCalculationStrategy} to calculate the fee.
 */
public interface FeeCalculationStrategy {

    /**
     * Calculates the price based on the given price.
     *
     * @param price the base price
     * @return the calculated price
     */
    BigDecimal calculatePrice(BigDecimal price);

}
