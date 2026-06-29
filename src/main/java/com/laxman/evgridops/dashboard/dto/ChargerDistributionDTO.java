package com.laxman.evgridops.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargerDistributionDTO {

    private String chargerType;
    private long count;

}
