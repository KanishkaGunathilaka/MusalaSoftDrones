package com.musalasoft.transportation.service;

import com.musalasoft.transportation.domain.BatteryLevelDTO;
import com.musalasoft.transportation.domain.Drone;
import com.musalasoft.transportation.domain.DroneDTO;
import com.musalasoft.transportation.domain.DroneMedication;

import java.util.List;

public interface DroneService {

    DroneDTO registerDrone(DroneDTO droneDTO);

    DroneDTO getDrone(Long id);

    DroneDTO loadMedications(DroneMedication droneMedication);

    List<DroneDTO> getAvailableDronesForLoading();

    BatteryLevelDTO getAvailableBatteryLevel(Long id);
}
