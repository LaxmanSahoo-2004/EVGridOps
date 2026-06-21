package com.laxman.evgridops.repository;

import com.laxman.evgridops.entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingStationRepository extends JpaRepository<ChargingStation,Long> {
}
