package com.laxman.evgridops.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDTO {

    private long totalStations;
    private long activeStations;
    private long inactiveStations;
    private long importedStations;
    private long manualStations;
    private long totalChargingPoints;

}