package org.springpractice.controllers;

import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springpractice.authentication.UserDetailsManagerImpl;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1")
public class UserController extends BaseController {

    @Autowired
    private UserDetailsManagerImpl userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create-user")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody Map<String, Object> userDetails) {
        return Try.of(() -> {
            String userName = (String) userDetails.get("user_name");
            String password = (String) userDetails.get("password");
            UserDetails user = User.withUsername(userName)
                    .password(passwordEncoder.encode(password)).build();
            userDetailsManager.createUser(user);
            return Map.of("status", "success");
        }).fold(this::onFailure, this::onSuccess);
    }
}
