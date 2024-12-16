package org.team_project.uni_lodz_park_area.model.entity;

import com.project.parkinglot.model.enums.ParkStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents an entity named {@link ParkEntity} for park.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PARK")
public class ParkEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "CHECK_IN")
    private LocalDateTime checkIn;

    @Column(name = "CHECK_OUT")
    private LocalDateTime checkOut;

    @Column(
            name = "TOTAL_COST",
            nullable = false,
            scale = 24,
            precision = 4
    )
    private BigDecimal totalCost;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "PARKING_AREA_ID",
            referencedColumnName = "ID"
    )
    private ParkingAreaEntity parkingAreaEntity;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "VEHICLE_ID",
            referencedColumnName = "ID"
    )
    private VehicleEntity vehicleEntity;

    @Column(name = "PARK_STATUS")
    @Enumerated(EnumType.STRING)
    private ParkStatus parkStatus;

}
