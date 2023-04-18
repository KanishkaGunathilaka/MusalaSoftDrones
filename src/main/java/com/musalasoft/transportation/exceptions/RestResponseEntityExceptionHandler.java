package com.musalasoft.transportation.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({ RecordNotFoundException.class })
    public ResponseEntity<Object> handleRecordNotFoundException(Exception ex) {

        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(mapErrorResponse(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ConditionNotMetException.class })
    public ResponseEntity<Object> handleConditionNotMetException(Exception ex) {

        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(mapErrorResponse(ex), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValidException(Exception ex) {

        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(mapErrorResponse("Please check the request. Validation failed"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeExceptionException(Exception ex) {

        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(mapErrorResponse("Unexpected exception occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleException(Exception ex) {

        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(mapErrorResponse("Unexpected exception occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static ErrorResponse mapErrorResponse(Exception ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());

        return errorResponse;
    }

    private static ErrorResponse mapErrorResponse(String ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex);

        return errorResponse;
    }
}