package org.team_project.uni_lodz_park_area.model.mapper.parking_area;

import org.team_project.uni_lodz_park_area.model.dto.request.parking_area.ParkingAreaCreateRequest;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link ParkingAreaCreateRequestToParkingAreaEntityMapper} to convert {@link ParkingAreaCreateRequest} objects to {@link ParkingAreaEntity} objects.
 */
@Mapper
public interface ParkingAreaCreateRequestToParkingAreaEntityMapper extends BaseMapper<ParkingAreaCreateRequest, ParkingAreaEntity> {

    /**
     * Maps a {@link ParkingAreaCreateRequest} object to a {@link ParkingAreaEntity} object.
     *
     * @param source The {@link ParkingAreaCreateRequest} object to map.
     * @return The mapped {@link ParkingAreaEntity} object.
     */
    @Override
    @Mapping(target = "priceListEntity", ignore = true)
    ParkingAreaEntity map(ParkingAreaCreateRequest source);

    /**
     * Initializes the mapper.
     *
     * @return the initialized mapper object.
     */
    static ParkingAreaCreateRequestToParkingAreaEntityMapper initialize() {
        return Mappers.getMapper(ParkingAreaCreateRequestToParkingAreaEntityMapper.class);
    }

}
