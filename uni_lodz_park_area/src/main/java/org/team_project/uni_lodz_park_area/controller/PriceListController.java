package org.team_project.uni_lodz_park_area.controller;

import org.team_project.uni_lodz_park_area.model.PriceList;
import org.team_project.uni_lodz_park_area.payload.response.CustomResponse;
import org.team_project.uni_lodz_park_area.service.priceList.PriceListGetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class named {@link PriceListController} for managing price lists.
 */
@RestController
@RequestMapping("/api/v1/price-list")
@RequiredArgsConstructor
@Validated
@Tag(name = "Price List Management", description = "APIs for managing price lists")
public class PriceListController {

    private final PriceListGetService priceListGetService;

    /**
     * Retrieves a price list by parking area ID.
     *
     * @param parkingAreaId The ID of the parking area
     * @return A CustomResponse containing the price list
     */
    @GetMapping("/parking-area/{parkingAreaId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DRIVER')")
    @Operation(summary = "Get price list by parking area ID",
            description = "Retrieves the price list associated with a specific parking area.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Price list found",
                            content = @Content(schema = @Schema(implementation = CustomResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Parking area or price list not found"),
                    @ApiResponse(responseCode = "403", description = "Not authorized to perform this action")
            })
    public CustomResponse<PriceList> getPriceListByParkingAreaId(
            @PathVariable("parkingAreaId") @UUID final String parkingAreaId) {
        final PriceList priceList = priceListGetService.getPriceListByParkingAreaId(parkingAreaId);
        return CustomResponse.ok(priceList);
    }
} 