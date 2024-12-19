package org.team_project.uni_lodz_park_area.model.mapper.parking_area;

import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link ParkingAreaEntityToParkingAreaMapper} to convert {@link ParkingAreaEntity} objects to {@link ParkingArea} objects.
 */
@Mapper
public interface ParkingAreaEntityToParkingAreaMapper extends BaseMapper<ParkingAreaEntity, ParkingArea> {

    /**
     * Maps a {@link ParkingAreaEntity} object to a {@link ParkingArea} object.
     *
     * @param source The {@link ParkingAreaEntity} object to map.
     * @return The mapped {@link ParkingArea} object.
     */
    @Override
    @Mapping(target = "priceList", ignore = true)
    ParkingArea map(ParkingAreaEntity source);

    /**
     * Initializes the mapper.
     *
     * @return The initialized mapper object.
     */
    static ParkingAreaEntityToParkingAreaMapper initialize() {
        return Mappers.getMapper(ParkingAreaEntityToParkingAreaMapper.class);
    }

}
