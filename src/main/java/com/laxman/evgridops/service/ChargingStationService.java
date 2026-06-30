package com.laxman.evgridops.service;

import com.laxman.evgridops.dto.NearbyStationRequestDTO;
import com.laxman.evgridops.dto.ChargingStationRequestDTO;
import com.laxman.evgridops.dto.ChargingStationResponseDTO;
import com.laxman.evgridops.dto.openchargemap.OpenChargeMapDTO;
import com.laxman.evgridops.entity.ChargingStation;
import com.laxman.evgridops.exception.StationNotFoundException;
import com.laxman.evgridops.repository.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ChargingStationService {

    @Value("${openchargemap.api.key}")
    private String apiKey;

    @Autowired
    private ChargingStationRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<OpenChargeMapDTO> fetchStations() {

        String url = "https://api.openchargemap.io/v3/poi?output=json&countrycode=IN&maxresults=7&key=" + apiKey;

        OpenChargeMapDTO[] response = restTemplate.getForObject(url, OpenChargeMapDTO[].class);

        List<OpenChargeMapDTO> stationList = Arrays.asList(response);

        for (OpenChargeMapDTO dto : stationList) {

            if (!repository.existsByOpenChargeMapId(dto.getId())) {

                ChargingStation station = convertToEntity(dto);

                repository.save(station);

            }

        }

        return stationList;
    }

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

    public long getInactiveStationCount() {

        return repository.countByStatus("INACTIVE");

    }

    public ChargingStationResponseDTO getStationById(Long id) {

        ChargingStation station = repository.findById(id).orElseThrow(() -> new StationNotFoundException("Station not found with id " + id));

        return convertToResponseDTO(station);
    }

    private ChargingStationResponseDTO convertToResponseDTO(ChargingStation station) {

        ChargingStationResponseDTO responseDTO = new ChargingStationResponseDTO();

        responseDTO.setId(station.getId());
        responseDTO.setName(station.getName());
        responseDTO.setCreatedAt(station.getCreatedAt());
        responseDTO.setLatitude(station.getLatitude());
        responseDTO.setLongitude(station.getLongitude());
        responseDTO.setChargerType(station.getChargerType());
        responseDTO.setCapacity(station.getCapacity());
        responseDTO.setStatus(station.getStatus());

        return responseDTO;
    }

    private ChargingStation convertToEntity(OpenChargeMapDTO dto) {

        ChargingStation station = new ChargingStation();

        station.setOpenChargeMapId(dto.getId());

        station.setName(dto.getAddressInfo().getTitle());

        station.setLatitude(dto.getAddressInfo().getLatitude());

        station.setLongitude(dto.getAddressInfo().getLongitude());

        if (dto.getStatusType().getIsOperational()) {
            station.setStatus("ACTIVE");
        } else {
            station.setStatus("INACTIVE");
        }

        if (dto.getConnections() != null && !dto.getConnections().isEmpty()) {

            station.setChargerType(dto.getConnections().get(0).getConnectionType().getTitle());

            station.setCapacity(dto.getConnections().get(0).getQuantity());

        }
        return station;
    }

    public long getTotalStationCount() {

        return repository.count();

    }

    public Page<ChargingStationResponseDTO> getStations(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ChargingStation> stationPage = repository.findAll(pageable);

        return stationPage.map(this::convertToResponseDTO);
    }

    public List<ChargingStationResponseDTO> findNearbyStations(
            NearbyStationRequestDTO request) {

        List<ChargingStation> stations = repository.findAll();

        List<ChargingStationResponseDTO> nearbyStations = new ArrayList<>();

        for (ChargingStation station : stations) {

            double distance = calculateDistance(
                    request.getLatitude(),
                    request.getLongitude(),
                    station.getLatitude(),
                    station.getLongitude()
            );

            if (distance <= request.getRadius()) {

                ChargingStationResponseDTO response = convertToResponseDTO(station);

                response.setDistanceInKm(distance);

                nearbyStations.add(response);
            }

        }

        return nearbyStations;
    }

    private double calculateDistance(
            double lat1,
            double lon1,
            double lat2,
            double lon2) {

        final int EARTH_RADIUS = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2)
                * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

}
