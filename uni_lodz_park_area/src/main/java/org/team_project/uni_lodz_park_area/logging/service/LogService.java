package org.team_project.uni_lodz_park_area.logging.service;

import org.team_project.uni_lodz_park_area.logging.entity.LogEntity;

/**
 * Service interface named {@link LogService} for managing log entities.
 */
public interface LogService {

    /**
     * Saves a log entity to the database.
     *
     * @param logEntity The log entity to be saved
     */
    void saveLogToDatabase(LogEntity logEntity);

}
