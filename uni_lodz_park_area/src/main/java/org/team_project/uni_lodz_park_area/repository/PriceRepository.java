package org.team_project.uni_lodz_park_area.repository;

import org.team_project.uni_lodz_park_area.model.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface named {@link PriceRepository} for managing prices.
 */
public interface PriceRepository extends JpaRepository<PriceEntity, String> {

}
