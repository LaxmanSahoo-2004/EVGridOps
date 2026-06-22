package com.laxman.evgridops.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChargingStationResponseDTO {

    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;

    private String chargerType;

    private Integer capacity;

    private String status;
}