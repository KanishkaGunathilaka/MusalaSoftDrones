package com.musalasoft.transportation.repository;

import com.musalasoft.transportation.domain.Medication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService{

    private final MedicationRepository medicationRepository;

    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public List<Medication> getMedicationList(List<Long> ids) {
        return medicationRepository.findAllByIdIn(ids);
    }
}
