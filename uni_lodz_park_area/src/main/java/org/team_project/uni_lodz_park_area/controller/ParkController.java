package org.team_project.uni_lodz_park_area.controller;

import org.team_project.uni_lodz_park_area.model.dto.request.park.ParkCheckInRequest;
import org.team_project.uni_lodz_park_area.model.dto.request.park.ParkCheckOutRequest;
import org.team_project.uni_lodz_park_area.model.dto.response.park.ParkCheckInResponse;
import org.team_project.uni_lodz_park_area.model.dto.response.park.ParkCheckOutResponse;
import org.team_project.uni_lodz_park_area.payload.response.CustomResponse;
import org.team_project.uni_lodz_park_area.service.park.ParkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class named {@link ParkController} for park management operations.
 */
@RestController
@RequestMapping("/api/v1/parks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Park Management", description = "APIs related to park check-ins and check-outs for drivers")
public class ParkController {

    private final ParkService parkService;

    /**
     * Allows a driver to check in to a park, recording the time and location.
     *
     * @param userId               The ID of the user checking in.
     * @param parkCheckInRequest   The ParkCheckInRequest containing check-in details.
     * @return A CustomResponse containing the check-in response.
     */
    @PostMapping("/userId/{userId}/check-in")
    @PreAuthorize("hasAuthority('ROLE_DRIVER')")
    @Operation(summary = "Check in to a park",
            description = "Allows a driver to check in to a park, recording the time and location.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful check-in",
                            content = @Content(schema = @Schema(implementation = CustomResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request provided"),
                    @ApiResponse(responseCode = "403", description = "Not authorized to perform this action")
            },
            security = @SecurityRequirement(name = "bearerAuth"))
    public CustomResponse<ParkCheckInResponse> checkIn(@PathVariable @UUID final String userId, @RequestBody @Valid ParkCheckInRequest parkCheckInRequest) {
        final ParkCheckInResponse parkCheckInResponse = parkService.checkIn(userId, parkCheckInRequest);
        return CustomResponse.ok(parkCheckInResponse);
    }

    /**
     * Allows a driver to check out from a park, recording the departure time.
     *
     * @param userId               The ID of the user checking out.
     * @param parkCheckOutRequest  The ParkCheckOutRequest containing check-out details.
     * @return A CustomResponse containing the check-out response.
     */
    @PostMapping("/userId/{userId}/check-out")
    @PreAuthorize("hasAuthority('ROLE_DRIVER')")
    @Operation(summary = "Check out from a park",
            description = "Allows a driver to check out from a park, recording the departure time.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful check-out",
                            content = @Content(schema = @Schema(implementation = CustomResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request provided"),
                    @ApiResponse(responseCode = "403", description = "Not authorized to perform this action")
            },
            security = @SecurityRequirement(name = "bearerAuth"))
    public CustomResponse<ParkCheckOutResponse> checkOut(
            @PathVariable("userId") @UUID final String userId,
            @RequestBody @Valid ParkCheckOutRequest parkCheckOutRequest
    ) {
        final ParkCheckOutResponse parkCheckOutResponse = parkService
                .checkOut(userId, parkCheckOutRequest);

        return CustomResponse.ok(parkCheckOutResponse);
    }

}
