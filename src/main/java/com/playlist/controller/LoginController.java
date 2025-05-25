package com.playlist.controller;

import com.playlist.controller.request.LoginRequest;
import com.playlist.controller.request.RegisterRequest;
import com.playlist.dto.UserDto;
import com.playlist.service.LoginService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/sign-in")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        UserDto userDto = loginService.login(request);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {

        String description = loginService.register(request);

        return new ResponseEntity<>(description, HttpStatus.CREATED);
    }

}
