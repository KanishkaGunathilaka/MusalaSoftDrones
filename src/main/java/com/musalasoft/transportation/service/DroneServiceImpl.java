package com.musalasoft.transportation.service;

import com.musalasoft.transportation.domain.Drone;
import com.musalasoft.transportation.domain.DroneDTO;
import com.musalasoft.transportation.mapper.DroneMapper;
import com.musalasoft.transportation.repository.DroneRepository;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public DroneDTO registerDrone(DroneDTO droneDTO) {

        Drone drone = DroneMapper.mapToDroneModel(droneDTO);
        drone = droneRepository.save(drone);
        return DroneMapper.mapToDroneDto(drone);
    }
}
