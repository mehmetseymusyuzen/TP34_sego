package org.team_project.uni_lodz_park_area.model.mapper.park;

import org.team_project.uni_lodz_park_area.model.Park;
import org.team_project.uni_lodz_park_area.model.entity.ParkEntity;
import org.team_project.uni_lodz_park_area.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link ParkToParkEntityMapper} to convert {@link Park} objects to {@link ParkEntity} objects.
 */
@Mapper
public interface ParkToParkEntityMapper extends BaseMapper<Park, ParkEntity> {

    /**
     * Maps a {@link Park} object to a {@link ParkEntity} object.
     *
     * @param source The {@link Park} object to map.
     * @return The mapped {@link ParkEntity} object.
     */
    @Override
    @Mapping(source = "parkingArea", target = "parkingAreaEntity")
    @Mapping(source = "vehicle", target = "vehicleEntity")
    ParkEntity map(Park source);

    /**
     * Initializes the mapper.
     *
     * @return An instance of {@link ParkToParkEntityMapper}.
     */
    static ParkToParkEntityMapper initialize() {
        return Mappers.getMapper(ParkToParkEntityMapper.class);
    }

}

