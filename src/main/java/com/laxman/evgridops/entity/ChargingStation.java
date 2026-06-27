package com.laxman.evgridops.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "charging_stations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // External API's unique ID
    private Integer openChargeMapId;

    private String name;
    private Double latitude;
    private Double longitude;
    private String chargerType;
    private Integer capacity;
    private String status;

}
