package com.musalasoft.transportation.exceptions;

public class ConditionNotMetException extends RuntimeException{

    public ConditionNotMetException(String message) {
        super(message);
    }
}
