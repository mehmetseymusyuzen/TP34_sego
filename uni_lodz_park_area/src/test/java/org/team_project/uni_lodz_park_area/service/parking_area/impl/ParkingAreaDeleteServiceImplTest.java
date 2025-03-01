package org.team_project.uni_lodz_park_area.service.parking_area.impl;

import org.team_project.uni_lodz_park_area.base.BaseServiceTest;
import org.team_project.uni_lodz_park_area.builder.ParkingAreaEntityBuilder;
import org.team_project.uni_lodz_park_area.exception.parkingarea.ParkingAreaNotFoundException;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.repository.ParkingAreaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

class ParkingAreaDeleteServiceImplTest extends BaseServiceTest {

    @InjectMocks
    private ParkingAreaDeleteServiceImpl parkingAreaDeleteService;

    @Mock
    private ParkingAreaRepository parkingAreaRepository;

    @Test
    void givenValidParkingAreaId_whenDeleteParkingAreaById_thenDeleteParkingAreaEntity() {

        // Given
        final String mockParkingAreaId = UUID.randomUUID().toString();

        final ParkingAreaEntity mockParkingAreaEntityToBeDelete = new ParkingAreaEntityBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(parkingAreaRepository.findById(mockParkingAreaId))
                .thenReturn(Optional.of(mockParkingAreaEntityToBeDelete));

        Mockito.doNothing().when(parkingAreaRepository).delete(mockParkingAreaEntityToBeDelete);

        // Then
        parkingAreaDeleteService.deleteParkingAreaById(mockParkingAreaId);

        // Verify
        Mockito.verify(parkingAreaRepository, times(1))
                .findById(mockParkingAreaId);
        Mockito.verify(parkingAreaRepository, times(1))
                .delete(mockParkingAreaEntityToBeDelete);

    }

    @Test
    void givenNotExistParkingAreaId_whenDeleteParkingAreaById_throwsParkingAreaNotFoundException() {

        // Given
        final String mockNonExistParkingAreaId = UUID.randomUUID().toString();

        // When
        Mockito.when(parkingAreaRepository.findById(mockNonExistParkingAreaId))
                .thenReturn(Optional.empty());

        // Then
        Assertions.assertThrowsExactly(
                ParkingAreaNotFoundException.class,
                () -> parkingAreaDeleteService.deleteParkingAreaById(mockNonExistParkingAreaId),
                "No ParkingAreaEntity found with ID: With given parkingAreaId: " + mockNonExistParkingAreaId
        );

        // Verify
        Mockito.verify(
                parkingAreaRepository,
                times(1)
        ).findById(mockNonExistParkingAreaId);

        Mockito.verify(
                parkingAreaRepository,
                never()
        ).delete(Mockito.any(ParkingAreaEntity.class));

    }

}