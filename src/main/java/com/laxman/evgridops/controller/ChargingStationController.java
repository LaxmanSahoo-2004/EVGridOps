package com.laxman.evgridops.controller;

import com.laxman.evgridops.dto.ChargingStationRequestDTO;
import com.laxman.evgridops.dto.ChargingStationResponseDTO;
import com.laxman.evgridops.entity.ChargingStation;
import com.laxman.evgridops.service.ChargingStationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class ChargingStationController {

    @Autowired
    private ChargingStationService service;


    @PostMapping
    public ChargingStationResponseDTO createStation(
           @Valid @RequestBody ChargingStationRequestDTO requestDTO) {

        return service.saveStation(requestDTO);
    }

    @GetMapping
    public List<ChargingStationResponseDTO> getAllStations() {
        return service.getAllStations();
    }
}