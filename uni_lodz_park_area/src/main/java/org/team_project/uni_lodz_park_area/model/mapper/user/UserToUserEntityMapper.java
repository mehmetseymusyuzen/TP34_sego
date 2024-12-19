package org.team_project.uni_lodz_park_area.model.mapper.user;

import org.team_project.uni_lodz_park_area.model.User;
import org.team_project.uni_lodz_park_area.model.mapper.BaseMapper;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link UserToUserEntityMapper} to map from {@link User} to {@link UserEntity}.
 */
@Mapper
public interface UserToUserEntityMapper extends BaseMapper<User, UserEntity> {

    /**
     * Maps a {@link User} domain model object to a {@link UserEntity} object.
     *
     * @param source The {@link User} domain model object to map.
     * @return The mapped {@link UserEntity} object.
     */
    @Override
    @Mapping(source = "vehicleList", target = "vehicles")
    UserEntity map(User source);

    /**
     * Initializes the mapper.
     *
     * @return The initialized mapper object.
     */
    static UserToUserEntityMapper initialize() {
        return Mappers.getMapper(UserToUserEntityMapper.class);
    }

}
