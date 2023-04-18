package com.musalasoft.transportation.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MedicationDTO {

    private String name;

    private double weight;

    private String code;

    private byte[] image;
}
