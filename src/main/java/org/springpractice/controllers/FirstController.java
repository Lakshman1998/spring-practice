package org.springpractice.controllers;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class FirstController {

    @GetMapping(value = "/get")
    public ResponseEntity getResponse() {
        return ResponseEntity.ok(ImmutableMap.of("status", "success"));
    }
}
