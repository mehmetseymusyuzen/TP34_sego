package org.team_project.uni_lodz_park_area.model.mapper.vehicle;

import org.team_project.uni_lodz_park_area.model.Vehicle;
import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link VehicleToVehicleEntityMapper} to map from {@link Vehicle} to {@link VehicleEntity}.
 */
@Mapper
public interface VehicleToVehicleEntityMapper extends BaseMapper<Vehicle,VehicleEntity> {

    /**
     * Maps a {@link Vehicle} object to a {@link VehicleEntity} object.
     *
     * @param source The {@link Vehicle} object to map.
     * @return The mapped {@link VehicleEntity} object.
     */
    @Override
    @Mapping(source = "user", target = "userEntity")
    VehicleEntity map(Vehicle source);

    /**
     * Initializes the mapper.
     *
     * @return The initialized mapper object.
     */
    static VehicleToVehicleEntityMapper initialize(){
        return Mappers.getMapper(VehicleToVehicleEntityMapper.class);
    }

}
