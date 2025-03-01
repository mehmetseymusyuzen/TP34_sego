package org.team_project.uni_lodz_park_area.service.user.impl;

import org.team_project.uni_lodz_park_area.base.BaseServiceTest;
import org.team_project.uni_lodz_park_area.builder.UserEntityBuilder;
import org.team_project.uni_lodz_park_area.builder.VehicleEntityBuilder;
import org.team_project.uni_lodz_park_area.exception.user.UserNotFoundException;
import org.team_project.uni_lodz_park_area.model.User;
import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.model.mapper.user.UserEntityToUserMapper;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.team_project.uni_lodz_park_area.service.auth.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

class UserGetServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private UserGetServiceImpl userGetService;

    @Mock
    private UserService userService;

    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();

    @Test
    void givenUser_whenGetUserById_thenReturnUser() {

        // Given
        final String mockUserId = UUID.randomUUID().toString();

        final VehicleEntity mockVehicleEntity = new VehicleEntityBuilder().withValidFields().build();

        final UserEntity mockUserEntity = new UserEntityBuilder()
                .withId(mockUserId)
                .customer()
                .withVehicle(mockVehicleEntity)
                .build();

        final User mockUser = userEntityToUserMapper.map(mockUserEntity);

        // When
        Mockito.when(userService.findById(mockUserId))
                .thenReturn(Optional.of(mockUserEntity));

        // Then
        final User expected = userGetService.getUserById(mockUserId);

        Assertions.assertEquals(mockUser.getId(), expected.getId());
        Assertions.assertEquals(mockUser.getUsername(), expected.getUsername());
        Assertions.assertEquals(mockUser.getEmail(), expected.getEmail());
        Assertions.assertEquals(mockUser.getRole(), expected.getRole());
        Assertions.assertEquals(mockUser.getFullName(), expected.getFullName());
        Assertions.assertEquals(mockUser.getVehicleList().size(), expected.getVehicleList().size());
        Assertions.assertEquals(mockUser.getVehicleList().get(0).getVehicleType(), expected.getVehicleList().get(0).getVehicleType());
        Assertions.assertEquals(mockUser.getVehicleList().get(0).getLicensePlate(), expected.getVehicleList().get(0).getLicensePlate());

        // Verify
        Mockito.verify(userService, Mockito.times(1)).findById(Mockito.anyString());

    }

    @Test
    void givenNonExistsUser_whenGetUserById_thenThrowsUserNotFoundException() {

        // Given
        final String mockGivenId = UUID.randomUUID().toString();

        // When
        Mockito.when(userService.findById(mockGivenId))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                UserNotFoundException.class,
                () -> userGetService.getUserById(mockGivenId)
        );

        // Verify
        Mockito.verify(userService, Mockito.times(1)).findById(Mockito.anyString());

    }

    @Test
    void givenAdmin_whenGetAdminById_thenReturnAdmin() {

        // Given
        final String mockAdminId = UUID.randomUUID().toString();

        final UserEntity mockAdminEntity = new UserEntityBuilder()
                .withId(mockAdminId)
                .admin()
                .build();

        final User mockAdmin = userEntityToUserMapper.map(mockAdminEntity);

        // When
        Mockito.when(
                        userService.findById(mockAdminId))
                .thenReturn(Optional.of(mockAdminEntity));

        // Then
        final User expected = userGetService.getAdminById(mockAdminId);

        Assertions.assertEquals(mockAdmin.getId(), expected.getId());
        Assertions.assertEquals(mockAdmin.getUsername(), expected.getUsername());
        Assertions.assertEquals(mockAdmin.getEmail(), expected.getEmail());
        Assertions.assertEquals(mockAdmin.getRole(), expected.getRole());

        // Verify
        Mockito.verify(
                userService, Mockito.times(1)
        ).findById(Mockito.anyString());

    }

    @Test
    void givenNonExistsAdmin_whenGetAdminById_thenThrowsUserNotFoundException() {

        // Given
        final String mockGivenId = UUID.randomUUID().toString();

        // When
        Mockito.when(userService.findById(mockGivenId))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                UserNotFoundException.class,
                () -> userGetService.getAdminById(mockGivenId)
        );

        // Verify
        Mockito.verify(userService, Mockito.times(1)).findById(Mockito.anyString());

    }

}
