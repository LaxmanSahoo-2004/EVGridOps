package com.laxman.evgridops.repository;

import com.laxman.evgridops.entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChargingStationRepository extends JpaRepository<ChargingStation,Long> {

    List<ChargingStation> findByStatus(String status);

    List<ChargingStation> findByChargerType(String chargerType);

    long countByStatus(String status);
}
