package com.musalasoft.transportation.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class DroneMedication {

    private long droneId;

    private List<Long> medicationList;
}
