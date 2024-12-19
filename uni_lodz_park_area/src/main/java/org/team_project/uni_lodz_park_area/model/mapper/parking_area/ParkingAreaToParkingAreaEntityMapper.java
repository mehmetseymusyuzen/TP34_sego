package org.team_project.uni_lodz_park_area.model.mapper.parking_area;

import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link ParkingAreaToParkingAreaEntityMapper} to convert {@link ParkingArea} objects to {@link ParkingAreaEntity} objects.
 */
@Mapper
public interface ParkingAreaToParkingAreaEntityMapper extends BaseMapper<ParkingArea, ParkingAreaEntity> {

    /**
     * Maps a {@link ParkingArea} object to a {@link ParkingAreaEntity} object.
     *
     * @param source The {@link ParkingArea} object to map.
     * @return The mapped {@link ParkingAreaEntity} object.
     */
    @Override
    ParkingAreaEntity map(ParkingArea source);

    /**
     * Initializes the mapper.
     *
     * @return The initialized mapper object.
     */
    static ParkingAreaToParkingAreaEntityMapper initialize(){
        return Mappers.getMapper(ParkingAreaToParkingAreaEntityMapper.class);
    }

}
