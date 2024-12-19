package org.team_project.uni_lodz_park_area.service.parking_area.impl;

import org.team_project.uni_lodz_park_area.base.BaseServiceTest;
import org.team_project.uni_lodz_park_area.builder.ParkingAreaEntityBuilder;
import org.team_project.uni_lodz_park_area.builder.ParkingAreaUpdateRequestBuilder;
import org.team_project.uni_lodz_park_area.exception.parkingarea.ParkingAreaNotFoundException;
import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.dto.request.parking_area.ParkingAreaUpdateRequest;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.mapper.parking_area.ParkingAreaEntityToParkingAreaMapper;
import org.team_project.uni_lodz_park_area.repository.ParkingAreaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;



class ParkingAreaUpdateServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private ParkingAreaUpdateServiceImpl parkingAreaUpdateService;

    @Mock
    private ParkingAreaRepository parkingAreaRepository;

    private final ParkingAreaEntityToParkingAreaMapper parkingAreaEntityToParkingAreaMapper =
            ParkingAreaEntityToParkingAreaMapper.initialize();



    @Test
    void givenValidParkingAreaUpdateRequest_whenUpdateParkingArea_thenReturnParkingArea(){

        //Given
        final String mockParkingAreaId = UUID.randomUUID().toString();

        final ParkingAreaUpdateRequest mockParkingAreaUpdateRequest = new ParkingAreaUpdateRequestBuilder()
                .withValidFields()
                .build();

        final ParkingAreaEntity mockParkingAreaEntity = new ParkingAreaEntityBuilder()
                .withValidFields()
                .witId(mockParkingAreaId)
                .build();

        final ParkingArea mockBeforeUpdatedParkingArea = parkingAreaEntityToParkingAreaMapper.map(mockParkingAreaEntity);

        // When
        Mockito.when(parkingAreaRepository.findById(mockParkingAreaId))
                .thenReturn(Optional.of(mockParkingAreaEntity));

        // Then
        final ParkingArea parkingAreaResponse =
                parkingAreaUpdateService.parkingAreaUpdateByCapacity(mockParkingAreaId,mockParkingAreaUpdateRequest);

        Assertions.assertNotNull(parkingAreaResponse);

        Assertions.assertEquals(
                mockParkingAreaUpdateRequest.getCapacity(),
                parkingAreaResponse.getCapacity()
        );

        Assertions.assertEquals(
                mockBeforeUpdatedParkingArea.getId(),
                parkingAreaResponse.getId()
        );

        Assertions.assertEquals(
                mockBeforeUpdatedParkingArea.getId(),
                parkingAreaResponse.getId()
        );

        Assertions.assertEquals(
                mockBeforeUpdatedParkingArea.getLocation(),
                parkingAreaResponse.getLocation()
        );

        Assertions.assertEquals(
                mockBeforeUpdatedParkingArea.getCity(),
                parkingAreaResponse.getCity()
        );

        // Verify
        Mockito.verify(parkingAreaRepository,Mockito.times(1)).findById(mockParkingAreaId);
        Mockito.verify(parkingAreaRepository,Mockito.times(1)).save(mockParkingAreaEntity);

    }

    @Test
    void givenEmptyParkingAreaUpdate_whenUpdateParkingArea_thenThrowParkingAreaNotFoundException(){

        // Given
        final ParkingAreaUpdateRequest mockParkingAreaUpdateRequest = new ParkingAreaUpdateRequestBuilder()
                .withValidFields()
                .build();

        final String mockParkingAreaUpdateRequestId = UUID.randomUUID().toString();

        // When
        Mockito.when(parkingAreaRepository.findById(mockParkingAreaUpdateRequestId)).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(ParkingAreaNotFoundException.class,
                () -> parkingAreaUpdateService.parkingAreaUpdateByCapacity(mockParkingAreaUpdateRequestId,mockParkingAreaUpdateRequest));

        // Verify
        Mockito.verify(parkingAreaRepository,Mockito.times(1)).findById(mockParkingAreaUpdateRequestId);
        Mockito.verify(parkingAreaRepository,Mockito.times(0)).save(Mockito.any(ParkingAreaEntity.class));

    }

}
