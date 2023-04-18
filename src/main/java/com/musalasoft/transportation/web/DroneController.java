package com.musalasoft.transportation.web;

import com.musalasoft.transportation.domain.BatteryLevelDTO;
import com.musalasoft.transportation.domain.DroneDTO;
import com.musalasoft.transportation.domain.DroneMedication;
import com.musalasoft.transportation.service.DroneService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/drone", produces = MediaType.APPLICATION_JSON_VALUE)
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/")
    public DroneDTO registerDrone(@Valid @RequestBody DroneDTO droneDTO) {
        return droneService.registerDrone(droneDTO);
    }

    @GetMapping("/{id}")
    public DroneDTO getDrone(@PathVariable Long id) {
        return droneService.getDrone(id);
    }

    @PostMapping("/load-medications")
    public DroneDTO loadMedications(@Valid @RequestBody DroneMedication droneMedication) {
        return droneService.loadMedications(droneMedication);
    }

    @GetMapping("/available-drones")
    public List<DroneDTO> getAvailableDronesForLoading() {
        return droneService.getAvailableDronesForLoading();
    }

    @GetMapping("/battery-level/{id}")
    public BatteryLevelDTO getAvailableBatteryLevel(@PathVariable Long id) {
        return droneService.getAvailableBatteryLevel(id);
    }
}
