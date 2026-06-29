package com.laxman.evgridops.repository;

import com.laxman.evgridops.dashboard.dto.ChargerDistributionDTO;
import com.laxman.evgridops.entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {

    List<ChargingStation> findByStatus(String status);

    List<ChargingStation> findByChargerType(String chargerType);

    long countByStatus(String status);

    boolean existsByOpenChargeMapId(Integer openChargeMapId);

    long countByOpenChargeMapIdIsNotNull();

    long countByOpenChargeMapIdIsNull();

    @Query("""
            SELECT COALESCE(SUM(c.capacity), 0)
            FROM ChargingStation c
            """)
    Integer getTotalChargingPoints();

    @Query("""
       SELECT new com.laxman.evgridops.dashboard.dto.ChargerDistributionDTO(
           c.chargerType,
           COUNT(c)
       )
       FROM ChargingStation c
       GROUP BY c.chargerType
       """)
    List<ChargerDistributionDTO> getChargerDistribution();


}
