package com.laxman.evgridops.dashboard.service;

import com.laxman.evgridops.dashboard.dto.ChargerDistributionDTO;
import com.laxman.evgridops.dashboard.dto.DashboardResponseDTO;
import com.laxman.evgridops.dashboard.dto.RecentImportDTO;
import com.laxman.evgridops.entity.ChargingStation;
import com.laxman.evgridops.repository.ChargingStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<ChargerDistributionDTO> getChargerDistribution() {

        return chargingStationRepository.getChargerDistribution();

    }

    public List<RecentImportDTO> getRecentImports() {

        List<ChargingStation> stations = chargingStationRepository.findTop5ByOpenChargeMapIdIsNotNullOrderByCreatedAtDesc();

        List<RecentImportDTO> response = new ArrayList<>();

        for (ChargingStation station : stations) {

            RecentImportDTO dto = new RecentImportDTO();

            dto.setName(station.getName());
            dto.setStatus(station.getStatus());
            dto.setCreatedAt(station.getCreatedAt());

            response.add(dto);
        }

        return response;
    }
}
