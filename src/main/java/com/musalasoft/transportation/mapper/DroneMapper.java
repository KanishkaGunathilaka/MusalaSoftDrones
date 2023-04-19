package com.musalasoft.transportation.mapper;

import com.musalasoft.transportation.domain.*;

import java.util.List;
import java.util.stream.Collectors;

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
                .id(drone.getId())
                .serialNumber(drone.getSerialNumber())
                .model(drone.getModel())
                .state(drone.getState())
                .weight(drone.getWeight())
                .capacity(drone.getCapacity())
                .medicationDTOList(mapToMedicationDto(drone.getMedicationList()))
                .build();
    }

    private static List<MedicationDTO> mapToMedicationDto(List<Medication> medicationList) {

        if(medicationList == null || medicationList.size() == 0) {
            return null;
        }

        return medicationList.stream()
                .map(DroneMapper::mapToMedicationDto)
                .collect(Collectors.toList());
    }

    private static MedicationDTO mapToMedicationDto(Medication medication) {

        return MedicationDTO.builder()
                .name(medication.getName())
                .code(medication.getCode())
                .weight(medication.getWeight())
                .image(medication.getImage())
                .build();
    }

    public static BatteryLevelDTO mapToBatteryLevelDto(Drone drone) {

        return BatteryLevelDTO.builder()
                .batteryLevel(drone.getCapacity())
                .build();
    }
}
