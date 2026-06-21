package com.laxman.evgridops.service;

import com.laxman.evgridops.entity.ChargingStation;
import com.laxman.evgridops.repository.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargingStationService {

    @Autowired
    private ChargingStationRepository repository;

    public ChargingStation saveStation(ChargingStation station) {
        return repository.save(station);
    }

    public List<ChargingStation> getAllStations() {
        return repository.findAll();
    }

}
