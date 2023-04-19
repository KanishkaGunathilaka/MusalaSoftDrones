package com.musalasoft.transportation.service;

import com.musalasoft.transportation.domain.Medication;

import java.util.List;

public interface MedicationService {

    List<Medication> getMedicationList(List<Long> ids);

    List<Medication> saveMedicationList(List<Medication> medicationList);
}
