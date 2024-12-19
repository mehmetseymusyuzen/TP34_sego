package org.team_project.uni_lodz_park_area.logging.service.impl;

import org.team_project.uni_lodz_park_area.logging.entity.LogEntity;
import org.team_project.uni_lodz_park_area.logging.repository.LogRepository;
import org.team_project.uni_lodz_park_area.logging.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service named {@link LogServiceImpl} implementation for managing log entities.
 */
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    /**
     * Saves a log entity to the database.
     *
     * @param logEntity The log entity to be saved
     */
    @Override
    public void saveLogToDatabase(LogEntity logEntity) {

        logEntity.setTime(LocalDateTime.now());
        logRepository.save(logEntity);

    }
}
