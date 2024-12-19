package org.team_project.uni_lodz_park_area.service.parking_area.impl;

import org.team_project.uni_lodz_park_area.base.BaseServiceTest;
import org.team_project.uni_lodz_park_area.builder.ParkingAreaCreateRequestBuilder;
import org.team_project.uni_lodz_park_area.builder.PriceListBuilder;
import org.team_project.uni_lodz_park_area.exception.parkingarea.ParkingAreaAlreadyExistException;
import org.team_project.uni_lodz_park_area.model.ParkingArea;
import org.team_project.uni_lodz_park_area.model.PriceList;
import org.team_project.uni_lodz_park_area.model.dto.request.parking_area.ParkingAreaCreateRequest;
import org.team_project.uni_lodz_park_area.model.dto.request.priceList.PriceListCreateRequest;
import org.team_project.uni_lodz_park_area.model.entity.ParkingAreaEntity;
import org.team_project.uni_lodz_park_area.model.mapper.parking_area.ParkingAreaCreateRequestToParkingAreaEntityMapper;
import org.team_project.uni_lodz_park_area.model.mapper.parking_area.ParkingAreaEntityToParkingAreaMapper;
import org.team_project.uni_lodz_park_area.repository.ParkingAreaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class ParkingAreaCreateServiceImplTest extends BaseServiceTest {


    @InjectMocks
    private ParkingAreaCreateServiceImpl parkingAreaCreateService;

    @Mock
    private ParkingAreaRepository parkingAreaRepository;

    @Mock
    private PriceListCreateService priceListCreateService;

    private final ParkingAreaCreateRequestToParkingAreaEntityMapper parkingAreaCreateRequestToParkingAreaEntityMapper =
            ParkingAreaCreateRequestToParkingAreaEntityMapper.initialize();

    private final ParkingAreaEntityToParkingAreaMapper parkingAreaEntityToParkingAreaMapper =
            ParkingAreaEntityToParkingAreaMapper.initialize();

    @Test
    public void givenValidParkingAreaCreateRequest_whenCreateParkingArea_thenReturnParkingAreaDomainModel() {

        // Given
        final ParkingAreaCreateRequest mockValidParkingAreaCreateRequest = new ParkingAreaCreateRequestBuilder()
                .withValidFields()
                .build();

        final PriceListCreateRequest mockPriceListCreateRequest = mockValidParkingAreaCreateRequest.getPriceListRequest();
        final PriceList mockPriceList = new PriceListBuilder().withValidFields().build();

        final ParkingAreaEntity mockParkingAreaEntity = parkingAreaCreateRequestToParkingAreaEntityMapper
                .map(mockValidParkingAreaCreateRequest);

        final ParkingArea mockParkingAreaDomainModel = parkingAreaEntityToParkingAreaMapper
                .map(mockParkingAreaEntity);


        // When
        Mockito.when(parkingAreaRepository.existsParkingAreaEntitiesByNameAndLocation(
                Mockito.anyString(),
                Mockito.anyString())
        ).thenReturn(Boolean.FALSE);

        Mockito.when(parkingAreaRepository.save(Mockito.any(ParkingAreaEntity.class)))
                .thenReturn(mockParkingAreaEntity);

        Mockito.when(priceListCreateService.createPriceList(mockPriceListCreateRequest))
                .thenReturn(mockPriceList);


        // Then
        ParkingArea response = parkingAreaCreateService
                .createParkingArea(mockValidParkingAreaCreateRequest);

        Assertions.assertEquals(
                mockParkingAreaDomainModel.getName(),
                response.getName()
        );

        Assertions.assertEquals(
                mockValidParkingAreaCreateRequest.getLocation(),
                response.getLocation()
        );

        // Verify
        Mockito.verify(
                parkingAreaRepository,
                Mockito.times(1)
        ).save(Mockito.any(ParkingAreaEntity.class));

        Mockito.verify(
                parkingAreaRepository,
                Mockito.times(1)
        ).existsParkingAreaEntitiesByNameAndLocation(Mockito.anyString(), Mockito.anyString());

    }

    @Test
    public void givenNotValidParkingAreaCreateRequest_whenCreateParkingArea_thenThrowsException() {

        // Given
        final ParkingAreaCreateRequest mockNotValidParkingAreaCreateRequest = new ParkingAreaCreateRequestBuilder()
                .withValidFields()
                .build();

        // When
        Mockito.when(parkingAreaRepository.existsParkingAreaEntitiesByNameAndLocation(
                Mockito.anyString(),
                Mockito.anyString())
        ).thenReturn(Boolean.TRUE);

        Assertions.assertThrowsExactly(
                ParkingAreaAlreadyExistException.class,
                () -> parkingAreaCreateService.createParkingArea(mockNotValidParkingAreaCreateRequest)
        );

        // Verify
        Mockito.verify(
                parkingAreaRepository,
                Mockito.times(1)
        ).existsParkingAreaEntitiesByNameAndLocation(Mockito.anyString(), Mockito.anyString());

        Mockito.verify(
                parkingAreaRepository,
                Mockito.times(0)
        ).save(Mockito.any(ParkingAreaEntity.class));

    }

}
