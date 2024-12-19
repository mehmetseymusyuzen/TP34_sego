package org.team_project.uni_lodz_park_area.model.dto.request.priceList;

import org.team_project.uni_lodz_park_area.model.dto.request.price.PriceCreateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A request class named {@link PriceListCreateRequest} representing the creation of a price list.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceListCreateRequest {
    @NotBlank
    private String name;

    @Valid
    private List<PriceCreateRequest> prices;
}
