package com.laxman.evgridops.service;

import com.laxman.evgridops.dto.ChargingStationRequestDTO;
import com.laxman.evgridops.dto.ChargingStationResponseDTO;
import com.laxman.evgridops.entity.ChargingStation;
import com.laxman.evgridops.repository.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChargingStationService {

    @Autowired
    private ChargingStationRepository repository;

    public ChargingStationResponseDTO saveStation(ChargingStationRequestDTO requestDTO) {

        // DTO -> Entity

        ChargingStation station = new ChargingStation();

        station.setName(requestDTO.getName());
        station.setLatitude(requestDTO.getLatitude());
        station.setLongitude(requestDTO.getLongitude());
        station.setChargerType(requestDTO.getChargerType());
        station.setCapacity(requestDTO.getCapacity());
        station.setStatus(requestDTO.getStatus());

        ChargingStation savedStation = repository.save(station);

        // Entity -> ResponseDTO

        ChargingStationResponseDTO responseDTO = new ChargingStationResponseDTO();

        responseDTO.setId(savedStation.getId());
        responseDTO.setName(savedStation.getName());
        responseDTO.setLatitude(savedStation.getLatitude());
        responseDTO.setLongitude(savedStation.getLongitude());
        responseDTO.setChargerType(savedStation.getChargerType());
        responseDTO.setCapacity(savedStation.getCapacity());
        responseDTO.setStatus(savedStation.getStatus());

        return responseDTO;
    }

    public List<ChargingStationResponseDTO> getAllStations() {

        List<ChargingStation> stations = repository.findAll();

        List<ChargingStationResponseDTO> responseList = new ArrayList<>();

        for (ChargingStation station : stations) {

            ChargingStationResponseDTO responseDTO = new ChargingStationResponseDTO();

            responseDTO.setId(station.getId());
            responseDTO.setName(station.getName());
            responseDTO.setLatitude(station.getLatitude());
            responseDTO.setLongitude(station.getLongitude());
            responseDTO.setChargerType(station.getChargerType());
            responseDTO.setCapacity(station.getCapacity());
            responseDTO.setStatus(station.getStatus());

            responseList.add(responseDTO);
        }

        return responseList;
    }

}
