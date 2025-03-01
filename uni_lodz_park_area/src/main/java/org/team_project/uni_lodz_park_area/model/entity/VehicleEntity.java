package org.team_project.uni_lodz_park_area.model.entity;

import org.team_project.uni_lodz_park_area.common.model.entity.BaseEntity;
import org.team_project.uni_lodz_park_area.model.enums.VehicleType;
import org.team_project.uni_lodz_park_area.security.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Represents an entity named {@link VehicleEntity} for vehicle.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VEHICLE")
public class VehicleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(
            name = "LICENCE_PLATE",
            nullable = false,
            unique = true,
            length = 10
    )
    private String licensePlate;

    @Column(name = "VEHICLE_TYPE")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "vehicleEntity"
    )
    private List<ParkEntity> parkEntities;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "USER_ID",
            referencedColumnName = "ID"
    )
    private UserEntity userEntity;

}
