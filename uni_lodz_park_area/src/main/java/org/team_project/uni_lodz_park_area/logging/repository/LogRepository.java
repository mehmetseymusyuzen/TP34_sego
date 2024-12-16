package org.team_project.uni_lodz_park_area.logging.repository;

import com.project.parkinglot.logging.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface named {@link LogRepository} for managing log entity persistence.
 */
public interface LogRepository extends JpaRepository<LogEntity, Long> {
}
