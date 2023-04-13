package com.musalasoft.transportation.domain;

public enum DroneModel {

    LIGHTWEIGHT("Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISEWEIGHT("Cruiserweight"),
    HEAVYWEIGHT("Heavyweight");

    public final String model;

    private DroneModel(String model) {
        this.model = model;
    }
}