package com.musalasoft.transportation.repository;

import com.musalasoft.transportation.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findAllByIdIn(List<Long> ids);
}
