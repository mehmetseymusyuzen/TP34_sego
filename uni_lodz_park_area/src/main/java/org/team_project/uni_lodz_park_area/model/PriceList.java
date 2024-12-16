package org.team_project.uni_lodz_park_area.model;

import com.project.parkinglot.common.model.BaseDomainModel;
import com.project.parkinglot.model.entity.PriceListEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents a pricelist domain model object named {@link PriceList}.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PriceList extends BaseDomainModel {
    private String id;
    private String name;
    private List<Price> prices;
}
