package org.team_project.uni_lodz_park_area.model.mapper.user;


import org.team_project.uni_lodz_park_area.model.User;
import org.team_project.uni_lodz_park_area.model.mapper.BaseMapper;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link UserEntityToUserMapper} to map from {@link UserEntity} to {@link User}.
 */
@Mapper
public interface UserEntityToUserMapper extends BaseMapper<UserEntity, User> {

    /**
     * Maps a {@link UserEntity} object to a {@link User} domain model.
     *
     * @param source The {@link UserEntity} object to map.
     * @return The mapped {@link User} domain model object.
     */
    @Override
    @Mapping(source = "vehicles", target = "vehicleList")
    User map(UserEntity source);

    /**
     * Initializes the mapper.
     *
     * @return The initialized mapper object.
     */
    static UserEntityToUserMapper initialize() {
        return Mappers.getMapper(UserEntityToUserMapper.class);
    }

}
