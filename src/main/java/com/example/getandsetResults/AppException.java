package com.example.getandsetResults;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class AppException {

    private AppException() {
    }

    public static ResponseStatusException userNotFound(Long userId) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " was not found");
    }

    public static ResponseStatusException analysisNotFount(Long analysisId){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Analysis with id " + analysisId + " was not found");
    }

    public static ResponseStatusException orderDoesNotExist(Long orderId){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Analysis with id " + orderId + " was not found");
    }
}
