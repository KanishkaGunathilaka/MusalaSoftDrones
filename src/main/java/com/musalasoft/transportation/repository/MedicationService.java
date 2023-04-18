package com.musalasoft.transportation.repository;

import com.musalasoft.transportation.domain.Medication;

import java.util.List;

public interface MedicationService {

    List<Medication> getMedicationList(List<Long> ids);
}
