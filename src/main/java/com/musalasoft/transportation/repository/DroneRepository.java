package com.musalasoft.transportation.repository;

import com.musalasoft.transportation.domain.Drone;
import com.musalasoft.transportation.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    List<Drone> findAllByStateIn(List<State> state);
}
