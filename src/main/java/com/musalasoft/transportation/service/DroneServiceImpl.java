package com.musalasoft.transportation.service;

import com.musalasoft.transportation.domain.*;
import com.musalasoft.transportation.exceptions.ConditionNotMetException;
import com.musalasoft.transportation.exceptions.RecordNotFoundException;
import com.musalasoft.transportation.mapper.DroneMapper;
import com.musalasoft.transportation.repository.DroneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationService medicationService;

    public DroneServiceImpl(DroneRepository droneRepository, MedicationService medicationService) {
        this.droneRepository = droneRepository;
        this.medicationService = medicationService;
    }

    @Override
    public DroneDTO registerDrone(DroneDTO droneDTO) {

        Drone drone = DroneMapper.mapToDroneModel(droneDTO);
        drone = droneRepository.save(drone);
        return DroneMapper.mapToDroneDto(drone);
    }

    @Override
    public DroneDTO getDrone(Long id) {

        return droneRepository.findById(id)
                .map(DroneMapper::mapToDroneDto)
                .orElseThrow(() -> new RecordNotFoundException("Cannot find Drone: " + id));
    }

    @Override
    public DroneDTO loadMedications(DroneMedication droneMedication) {

        Optional<Drone> drone = droneRepository.findById(droneMedication.getDroneId());
        drone.orElseThrow(() -> new RecordNotFoundException("Cannot find Drone: " + droneMedication.getDroneId()));

        //Here I assumed drone can be loaded only when state is either IDLE or LOADING
        drone.filter(d -> State.IDLE.equals(d.getState()) || State.LOADING.equals(d.getState()))
                        .orElseThrow(() -> new ConditionNotMetException("Drone " + drone.get().getId() + " is not in a loading state"));

        drone.filter(d -> d.getCapacity() > 25)
                .orElseThrow(() -> new ConditionNotMetException("Drone " + drone.get().getId() + " Cannot be loaded. Battery level is below 25%"));

        Drone updatedDrone = loadMedication(drone.get(), droneMedication);

        return DroneMapper.mapToDroneDto(updatedDrone);
    }

    private Drone loadMedication(Drone drone, DroneMedication droneMedication) {

        List<Medication> medicationList = medicationService.getMedicationList(droneMedication.getMedicationList());
        if(droneMedication.getMedicationList().size() != medicationList.size()) {
            throw new ConditionNotMetException("Cannot find some medications. Please check again");
        }
        Double medicationTotalWeight = medicationList.stream()
                .mapToDouble(medication -> medication.getWeight())
                .sum();

        Double medicationWeightDroneAlreadyHas = 0.0;

        if(drone.getMedicationList() != null) {
            medicationWeightDroneAlreadyHas = drone.getMedicationList().stream()
                    .mapToDouble(d -> d.getWeight())
                    .sum();
        }

        medicationList.stream().forEach(medication -> medication.toBuilder().drone(drone).build());

        Double totalWeight = medicationTotalWeight + medicationWeightDroneAlreadyHas;

        if(totalWeight > drone.getWeight()) {
            throw new ConditionNotMetException("Medications are heavier than drone can carry");
        }

        List<Medication> medications = drone.getMedicationList();
        medications.addAll(medicationList);

        drone.toBuilder()
                .state(State.LOADING)
                .medicationList(medications)
                .build();

        medicationService.saveMedicationList(medicationList);
        droneRepository.save(drone);
        Optional<Drone> updatedDrone = droneRepository.findById(droneMedication.getDroneId());

        return updatedDrone.get();
    }

    @Override
    public List<DroneDTO> getAvailableDronesForLoading() {

        List<Drone> droneList = droneRepository.findAllByStateIn(List.of(State.LOADING, State.IDLE));
        return droneList.stream()
                .map(DroneMapper::mapToDroneDto)
                .collect(Collectors.toList());
    }

    @Override
    public BatteryLevelDTO getAvailableBatteryLevel(Long id) {

        return droneRepository.findById(id)
                .map(DroneMapper::mapToBatteryLevelDto)
                .orElseThrow(() -> new RecordNotFoundException("Cannot find Drone: " + id));
    }
}
