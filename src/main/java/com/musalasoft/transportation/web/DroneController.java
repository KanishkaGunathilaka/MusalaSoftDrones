package com.musalasoft.transportation.web;

import com.musalasoft.transportation.domain.DroneDTO;
import com.musalasoft.transportation.service.DroneService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
