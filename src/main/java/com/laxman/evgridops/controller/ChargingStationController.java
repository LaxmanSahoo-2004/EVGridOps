package com.laxman.evgridops.controller;

import com.laxman.evgridops.dto.ChargingStationRequestDTO;
import com.laxman.evgridops.dto.ChargingStationResponseDTO;
import com.laxman.evgridops.service.ChargingStationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class ChargingStationController {

    @Autowired
    private ChargingStationService service;


    @PostMapping
    public ResponseEntity<ChargingStationResponseDTO> createStation(@Valid @RequestBody ChargingStationRequestDTO requestDTO) {

        ChargingStationResponseDTO response = service.saveStation(requestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ChargingStationResponseDTO>> getAllStations() {
        List<ChargingStationResponseDTO> stations = service.getAllStations();

        return ResponseEntity.ok(stations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChargingStationResponseDTO> updateStation(@PathVariable Long id, @Valid @RequestBody ChargingStationRequestDTO requestDTO) {

        ChargingStationResponseDTO response = service.updateStation(id, requestDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStation(@PathVariable Long id) {

        service.deleteStation(id);

        return ResponseEntity.ok("Station deleted successfully");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ChargingStationResponseDTO>> getStationsByStatus(@PathVariable String status) {

        List<ChargingStationResponseDTO> stations = service.getStationsByStatus(status);

        return ResponseEntity.ok(stations);
    }

    @GetMapping("/type/{chargerType}")
    public ResponseEntity<List<ChargingStationResponseDTO>> getStationsByChargerType(@PathVariable String chargerType) {

        List<ChargingStationResponseDTO> stations = service.getStationsByChargerType(chargerType);

        return ResponseEntity.ok(stations);
    }

    @GetMapping("/count/active")
    public ResponseEntity<Long> getActiveStationCount() {

        long count = service.getActiveStationCount();

        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChargingStationResponseDTO> getStationById(@PathVariable Long id) {

        ChargingStationResponseDTO station = service.getStationById(id);

        return ResponseEntity.ok(station);
    }

}