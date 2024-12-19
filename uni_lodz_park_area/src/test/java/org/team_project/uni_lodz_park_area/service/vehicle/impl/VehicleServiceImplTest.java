package org.team_project.uni_lodz_park_area.service.vehicle.impl;

import org.team_project.uni_lodz_park_area.base.BaseServiceTest;
import org.team_project.uni_lodz_park_area.builder.ParkEntityBuilder;
import org.team_project.uni_lodz_park_area.builder.UserEntityBuilder;
import org.team_project.uni_lodz_park_area.builder.VehicleEntityBuilder;
import org.team_project.uni_lodz_park_area.builder.VehicleRequestBuilder;
import org.team_project.uni_lodz_park_area.exception.user.UserNotFoundException;
import org.team_project.uni_lodz_park_area.exception.vehicle.VehicleAlreadyExist;
import org.team_project.uni_lodz_park_area.model.Vehicle;
import org.team_project.uni_lodz_park_area.model.dto.request.vehicle.VehicleRequest;
import org.team_project.uni_lodz_park_area.model.dto.response.ParkDetailResponse;
import org.team_project.uni_lodz_park_area.model.dto.response.VehicleParkingDetailResponse;
import org.team_project.uni_lodz_park_area.model.entity.ParkEntity;
import org.team_project.uni_lodz_park_area.model.entity.VehicleEntity;
import org.team_project.uni_lodz_park_area.model.mapper.park.ParkEntityToParkDetailResponse;
import org.team_project.uni_lodz_park_area.model.mapper.vehicle.VehicleEntityToVehicleMapper;
import org.team_project.uni_lodz_park_area.model.mapper.vehicle.VehicleRequestToVehicleMapper;
import org.team_project.uni_lodz_park_area.model.mapper.vehicle.VehicleToVehicleEntityMapper;
import org.team_project.uni_lodz_park_area.repository.VehicleRepository;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import org.team_project.uni_lodz_park_area.service.auth.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class VehicleServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private UserService userService;

    final VehicleEntityToVehicleMapper vehicleEntityToVehicleMapper =
            VehicleEntityToVehicleMapper.initialize();

     private final VehicleToVehicleEntityMapper vehicleToVehicleEntityMapper =
             VehicleToVehicleEntityMapper.initialize();

     private final VehicleRequestToVehicleMapper vehicleRequestToVehicleMapper=
             VehicleRequestToVehicleMapper.initialize();

     private final ParkEntityToParkDetailResponse parkEntityToParkDetailResponse =
             ParkEntityToParkDetailResponse.initialize();


    @Test
    void givenValidVehicleRequest_whenAssignVehicleToUser_thenReturnVehicle(){

        // Given
        final String mockUserId = UUID.randomUUID().toString();

        final UserEntity mockUserEntity = new UserEntityBuilder()
                .withId(mockUserId)
                .customer()
                .build();

        final VehicleRequest mockVehicleRequest = new VehicleRequestBuilder()
                .withValidFields()
                .build();

        final Vehicle mockVehicle = vehicleRequestToVehicleMapper.map(mockVehicleRequest);

        final VehicleEntity mockVehicleEntity = vehicleToVehicleEntityMapper.map(mockVehicle);

        final Vehicle mockVehicleToCreated = vehicleEntityToVehicleMapper.map(mockVehicleEntity);

        // When
        Mockito.when(userService.findById(mockUserId))
                .thenReturn(Optional.of(mockUserEntity));

        Mockito.when(vehicleRepository.existsByLicensePlate(mockVehicleRequest.getLicensePlate()))
                .thenReturn(Boolean.FALSE);

        // Then
        final Vehicle vehicleToBeAssigned = vehicleService.assignVehicleToUser(mockUserId,mockVehicleRequest);

        Assertions.assertEquals(mockVehicleRequest.getLicensePlate(),vehicleToBeAssigned.getLicensePlate());
        Assertions.assertEquals(mockVehicleRequest.getVehicleType(),vehicleToBeAssigned.getVehicleType());
        Assertions.assertEquals(mockVehicleRequest.getLicensePlate(),mockVehicleEntity.getLicensePlate());
        Assertions.assertEquals(mockVehicleRequest.getVehicleType(),mockVehicleEntity.getVehicleType());
        Assertions.assertEquals(mockVehicleEntity.getId() , mockVehicleToCreated.getId());
        Assertions.assertEquals(mockVehicleEntity.getLicensePlate() , mockVehicleToCreated.getLicensePlate());
        Assertions.assertEquals(mockVehicleEntity.getVehicleType() , mockVehicleToCreated.getVehicleType());
        Assertions.assertEquals(mockVehicleEntity.getParkEntities() , mockVehicleToCreated.getParkList());
        Assertions.assertEquals(mockVehicleEntity.getUserEntity() , mockVehicleToCreated.getUser());

        // Verify
        Mockito.verify(userService,Mockito.times(1)).findById(Mockito.anyString());
        Mockito.verify(vehicleRepository,Mockito.times(1)).save(Mockito.any(VehicleEntity.class));

    }

    @Test
    void givenNonExitUser_whenAssignVehicleToUser_thenThrowsUserNotFoundException(){

        // Given
        final String mockGivenId = UUID.randomUUID().toString();

        final VehicleRequest mockVehicleRequest = new VehicleRequestBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(userService.findById(mockGivenId))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                UserNotFoundException.class,
                () -> vehicleService.assignVehicleToUser(mockGivenId,mockVehicleRequest)
        );

        // Verify
        Mockito.verify(userService,Mockito.times(1)).findById(Mockito.anyString());

    }

    @Test
    void givenValidVehicleRequest_whenAssignVehicleToUser_ThenThrowVehicleAlreadyExistException(){

        // Given
        final String mockUserId = UUID.randomUUID().toString();

        final UserEntity mockUserEntity = new UserEntityBuilder()
                .withId(mockUserId)
                .customer()
                .build();

        final VehicleRequest mockVehicleRequest = new VehicleRequestBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(userService.findById(mockUserId))
                 .thenReturn(Optional.of(mockUserEntity));

        Mockito.when(vehicleRepository.existsByLicensePlate(mockVehicleRequest.getLicensePlate()))
                .thenReturn(Boolean.TRUE);

        // Then
        Assertions.assertThrowsExactly(
                VehicleAlreadyExist.class,
                ()->vehicleService.assignVehicleToUser(mockUserId,mockVehicleRequest)
        );

        // Verify
        Mockito.verify(userService,Mockito.times(1)).findById(Mockito.anyString());
        Mockito.verify(vehicleRepository,Mockito.never()).save(Mockito.any(VehicleEntity.class));

    }

     @Test
     void givenValidLicensePlate_whenGetParkingDetails_thenReturnParkingDetails() {

        // Given
        final String mockLicensePlate = UUID.randomUUID().toString();

        final ParkEntity mockParkEntity1 = new ParkEntityBuilder()
                 .withValidFields().build();
        final ParkEntity mockParkEntity2 = new ParkEntityBuilder()
                 .withValidFields().build();

        final List<ParkEntity> mockParkEntities = Arrays.asList(mockParkEntity1, mockParkEntity2);

        final VehicleEntity mockVehicleEntity = new VehicleEntityBuilder()
                 .withValidFields()
                 .withLicensePlate(mockLicensePlate)
                 .build();

        mockVehicleEntity.setParkEntities(mockParkEntities);

        List<ParkDetailResponse> expectedParkDetails = Arrays.asList(
                 parkEntityToParkDetailResponse.map(mockParkEntity1),
                 parkEntityToParkDetailResponse.map(mockParkEntity2)
        );

        // When
        Mockito.when(vehicleRepository.findByLicensePlate(mockLicensePlate))
                .thenReturn(Optional.of(mockVehicleEntity));

        // Then
        final VehicleParkingDetailResponse parkingDetails = vehicleService.getParkingDetails(mockLicensePlate);


        Assertions.assertNotNull(parkingDetails);
        Assertions.assertEquals(mockLicensePlate, parkingDetails.getLicensePlate());
        for(int i = 0; i < expectedParkDetails.size(); i++){
            Assertions.assertEquals(expectedParkDetails.get(i).getCheckInDate(), parkingDetails.getParkDetails().get(i).getCheckInDate());
            Assertions.assertEquals(expectedParkDetails.get(i).getCheckOutDate(), parkingDetails.getParkDetails().get(i).getCheckOutDate());
            Assertions.assertEquals(expectedParkDetails.get(i).getParkingAreaName(), parkingDetails.getParkDetails().get(i).getParkingAreaName());
            Assertions.assertEquals(expectedParkDetails.get(i).getTotalCost(), parkingDetails.getParkDetails().get(i).getTotalCost());
        }


        // Verify
        Mockito.verify(vehicleRepository, Mockito.times(1)).findByLicensePlate(mockLicensePlate);

     }

}
