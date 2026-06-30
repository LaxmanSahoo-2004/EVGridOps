package com.laxman.evgridops.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChargingStationResponseDTO {

    private Long id;

    private String name;

    private LocalDateTime createdAt;

    private Double latitude;

    private Double longitude;

    private String chargerType;

    private Integer capacity;

    private String status;

    private Double distanceInKm;
}