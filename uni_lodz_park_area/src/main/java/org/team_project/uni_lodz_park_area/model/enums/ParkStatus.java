package org.team_project.uni_lodz_park_area.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration named {@link ParkStatus} representing the status of a park.
 */
@Getter
@RequiredArgsConstructor
public enum ParkStatus {
    EMPTY,
    FULL
}
