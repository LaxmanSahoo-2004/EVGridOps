package com.laxman.evgridops.dashboard.controller;

import com.laxman.evgridops.dashboard.dto.ChargerDistributionDTO;
import com.laxman.evgridops.dashboard.dto.DashboardResponseDTO;
import com.laxman.evgridops.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<DashboardResponseDTO> getDashboardSummary() {

        return ResponseEntity.ok(dashboardService.getDashboardSummary());

    }

    @GetMapping("/charger-distribution")
    public ResponseEntity<List<ChargerDistributionDTO>> getChargerDistribution() {

        return ResponseEntity.ok(dashboardService.getChargerDistribution());

    }

}