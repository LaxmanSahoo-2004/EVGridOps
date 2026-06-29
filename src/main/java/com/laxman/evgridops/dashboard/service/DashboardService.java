package com.laxman.evgridops.dashboard.service;

import com.laxman.evgridops.dashboard.dto.DashboardResponseDTO;
import com.laxman.evgridops.repository.ChargingStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    public DashboardResponseDTO getDashboardSummary() {

        DashboardResponseDTO response = new DashboardResponseDTO();

        response.setTotalStations(chargingStationRepository.count());

        response.setActiveStations(chargingStationRepository.countByStatus("ACTIVE"));

        response.setInactiveStations(chargingStationRepository.countByStatus("INACTIVE"));

        response.setImportedStations(chargingStationRepository.countByOpenChargeMapIdIsNotNull());

        response.setManualStations(chargingStationRepository.countByOpenChargeMapIdIsNull());

        response.setTotalChargingPoints(chargingStationRepository.getTotalChargingPoints());

        return response;
    }
}
