package org.team_project.uni_lodz_park_area.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * Represents a price domain model object named {@link Price}.
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Price extends BaseDomainModel {
    private String id;
    private Integer lowerBound;
    private Integer upperBound;
    private BigDecimal cost;
}
