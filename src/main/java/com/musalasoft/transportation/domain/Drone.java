package com.musalasoft.transportation.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serialNumber;

    private DroneModel model;

    private Double weight;

    private Double capacity;

    private State state;

    @OneToMany(mappedBy="drone")
    private List<Medication> medicationList;
}