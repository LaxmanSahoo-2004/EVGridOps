package com.laxman.evgridops.service;

import com.laxman.evgridops.dto.ChargingStationRequestDTO;
import com.laxman.evgridops.dto.ChargingStationResponseDTO;
import com.laxman.evgridops.entity.ChargingStation;
import com.laxman.evgridops.exception.StationNotFoundException;
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
        return convertToResponseDTO(savedStation);

    }

    public List<ChargingStationResponseDTO> getAllStations() {

        List<ChargingStation> stations = repository.findAll();

        List<ChargingStationResponseDTO> responseList = new ArrayList<>();

        for (ChargingStation station : stations) {

            responseList.add(convertToResponseDTO(station));
        }

        return responseList;
    }

    public ChargingStationResponseDTO updateStation(Long id, ChargingStationRequestDTO requestDTO) {

        ChargingStation station = repository.findById(id).orElseThrow(() -> new StationNotFoundException("Station not found with id " + id));

        station.setName(requestDTO.getName());
        station.setLatitude(requestDTO.getLatitude());
        station.setLongitude(requestDTO.getLongitude());
        station.setChargerType(requestDTO.getChargerType());
        station.setCapacity(requestDTO.getCapacity());
        station.setStatus(requestDTO.getStatus());

        ChargingStation updatedStation = repository.save(station);

        return convertToResponseDTO(station);

    }

    public void deleteStation(Long id) {

        ChargingStation station = repository.findById(id).orElseThrow(() -> new StationNotFoundException("Station not found with id " + id));

        repository.delete(station);
    }

    public List<ChargingStationResponseDTO> getStationsByStatus(String status) {

        List<ChargingStation> stations = repository.findByStatus(status);

        List<ChargingStationResponseDTO> responseList = new ArrayList<>();

        for (ChargingStation station : stations) {

            responseList.add(convertToResponseDTO(station));
        }

        return responseList;
    }

    public List<ChargingStationResponseDTO> getStationsByChargerType(String chargerType) {

        List<ChargingStation> stations = repository.findByChargerType(chargerType);

        List<ChargingStationResponseDTO> responseList = new ArrayList<>();

        for (ChargingStation station : stations) {

            responseList.add(convertToResponseDTO(station));
        }

        return responseList;
    }

    public long getActiveStationCount() {

        return repository.countByStatus("ACTIVE");
    }

    public ChargingStationResponseDTO getStationById(Long id) {

        ChargingStation station = repository.findById(id).orElseThrow(() -> new StationNotFoundException("Station not found with id " + id));

        return convertToResponseDTO(station);
    }

    private ChargingStationResponseDTO convertToResponseDTO(ChargingStation station) {

        ChargingStationResponseDTO responseDTO = new ChargingStationResponseDTO();

        responseDTO.setId(station.getId());
        responseDTO.setName(station.getName());
        responseDTO.setLatitude(station.getLatitude());
        responseDTO.setLongitude(station.getLongitude());
        responseDTO.setChargerType(station.getChargerType());
        responseDTO.setCapacity(station.getCapacity());
        responseDTO.setStatus(station.getStatus());

        return responseDTO;
    }

}
