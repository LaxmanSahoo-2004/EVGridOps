package com.laxman.evgridops.controller;

import com.laxman.evgridops.entity.ChargingStation;
import com.laxman.evgridops.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class ChargingStationController {

    @Autowired
    private ChargingStationService service;


    @PostMapping
    public ChargingStation createStation(
            @RequestBody ChargingStation station) {

        return service.saveStation(station);
    }

    @GetMapping
    public List<ChargingStation> getAllStations() {
        return service.getAllStations();
    }
}