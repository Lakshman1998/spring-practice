package org.springpractice.controllers;

import org.springframework.http.ResponseEntity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseController {

    private static Logger LOGGER = Logger.getLogger(BaseController.class.getCanonicalName());

    public <T> ResponseEntity<T> onSuccess(T response) {
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> onFailure(Throwable throwable) {
        LOGGER.log(Level.SEVERE, String.format("Error occurred at: %s", throwable));
        return ResponseEntity.internalServerError()
                .body("Something went wrong");
    }
}
