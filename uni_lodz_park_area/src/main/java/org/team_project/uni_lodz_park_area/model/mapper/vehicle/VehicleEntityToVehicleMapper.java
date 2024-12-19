package org.team_project.uni_lodz_park_area.model.mapper.vehicle;

import org.team_project.uni_lodz_park_area.model.Vehicle;
import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link VehicleEntityToVehicleMapper} to map from {@link VehicleEntity} to {@link Vehicle}.
 */
@Mapper
public interface VehicleEntityToVehicleMapper extends BaseMapper<VehicleEntity, Vehicle> {

    /**
     * Maps a {@link VehicleEntity} object to a {@link Vehicle} domain model object.
     *
     * @param source The {@link VehicleEntity} object to map.
     * @return The mapped {@link Vehicle} domain model object.
     */
    @Override
    @Mapping(source = "parkEntities",target = "parkList")
    Vehicle map(VehicleEntity source);

    /**
     * Initializes the mapper.
     *
     * @return The initialized mapper object.
     */
    static VehicleEntityToVehicleMapper initialize(){
        return Mappers.getMapper(VehicleEntityToVehicleMapper.class);
    }

}
