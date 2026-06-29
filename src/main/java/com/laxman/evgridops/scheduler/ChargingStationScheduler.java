package com.laxman.evgridops.scheduler;

import com.laxman.evgridops.service.ChargingStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChargingStationScheduler {

    @Autowired
    private ChargingStationService chargingStationService;

    @Scheduled(cron = "${scheduler.import.cron}")
    public void importStationsAutomatically() {

        try {
            chargingStationService.fetchStations();
            log.info("Automatic import completed.");
        } catch (Exception e) {
            log.error("Automatic import failed", e);
        }
    }
}
