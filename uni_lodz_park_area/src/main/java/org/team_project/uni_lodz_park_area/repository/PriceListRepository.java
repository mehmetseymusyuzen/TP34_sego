package org.team_project.uni_lodz_park_area.repository;

import org.team_project.uni_lodz_park_area.model.entity.PriceListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface named {@link PriceListRepository} for managing price lists.
 */
public interface PriceListRepository extends JpaRepository<PriceListEntity, String> {
}
