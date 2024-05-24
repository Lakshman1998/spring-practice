package org.springpractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springpractice.services.FirstService;

@RestController
@RequestMapping(value = "/v1")
public class FirstController {

    private FirstService firstService;

    @Autowired
    FirstController(FirstService firstService) {
        this.firstService = firstService;
    }

    @GetMapping(value = "/get")
    public ResponseEntity getResponse() {
        return ResponseEntity.ok(firstService.executeFirstService());
    }
}
