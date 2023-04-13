package com.musalasoft.transportation.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class DroneDTO {

    @Length(max = 100)
    @NotBlank
    private String serialNumber;

    private DroneModel model;

    @Max(value = 500)
    private Double weight;

    @Max(value = 100)
    private Double capacity;

    private State state;
}