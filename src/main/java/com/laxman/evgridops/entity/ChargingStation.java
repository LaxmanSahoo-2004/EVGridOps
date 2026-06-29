package com.laxman.evgridops.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    private String name;
    private Double latitude;
    private Double longitude;
    private String chargerType;
    private Integer capacity;
    private String status;

}
