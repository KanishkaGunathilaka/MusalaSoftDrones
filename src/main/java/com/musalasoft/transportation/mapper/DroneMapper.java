package com.musalasoft.transportation.mapper;

import com.musalasoft.transportation.domain.Drone;
import com.musalasoft.transportation.domain.DroneDTO;

public class DroneMapper {

    public static Drone mapToDroneModel(DroneDTO droneDTO) {

        return Drone.builder()
                .serialNumber(droneDTO.getSerialNumber())
                .model(droneDTO.getModel())
                .state(droneDTO.getState())
                .weight(droneDTO.getWeight())
                .capacity(droneDTO.getCapacity())
                .build();
    }

    public static DroneDTO mapToDroneDto(Drone drone) {

        return DroneDTO.builder()
                .serialNumber(drone.getSerialNumber())
                .model(drone.getModel())
                .state(drone.getState())
                .weight(drone.getWeight())
                .capacity(drone.getCapacity())
                .build();
    }
}
