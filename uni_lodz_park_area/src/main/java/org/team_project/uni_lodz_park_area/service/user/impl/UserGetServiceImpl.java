package org.team_project.uni_lodz_park_area.service.user.impl;

import org.team_project.uni_lodz_park_area.exception.user.UserNotFoundException;
import org.team_project.uni_lodz_park_area.model.User;
import org.team_project.uni_lodz_park_area.model.mapper.user.UserEntityToUserMapper;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.team_project.uni_lodz_park_area.service.auth.UserService;
import org.team_project.uni_lodz_park_area.service.user.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link UserGetServiceImpl} for getting user.
 */
@Service
@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {

    private final UserService userService;

    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();

    /**
     * Retrieves a user by user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the user
     * @throws UserNotFoundException if the user with the given ID is not found
     */
    @Override
    public User getUserById(String userId) {

        final UserEntity userEntity = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return userEntityToUserMapper.map(userEntity);
    }

    /**
     * Retrieves an admin user by admin ID.
     *
     * @param adminId the ID of the admin user to retrieve
     * @return the admin user
     * @throws UserNotFoundException if the admin user with the given ID is not found
     */
    @Override
    public User getAdminById(final String adminId) {

        final UserEntity adminEntity = userService.findById(adminId)
                .orElseThrow(() -> new UserNotFoundException(adminId));

        return userEntityToUserMapper.map(adminEntity);
    }

}
