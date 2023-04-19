package com.musalasoft.transportation.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private double weight;

    private String code;

    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="id", insertable = false, updatable = false)
    private Drone drone;
}