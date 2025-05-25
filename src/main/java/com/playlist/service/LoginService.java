package com.playlist.service;

import com.playlist.controller.request.LoginRequest;
import com.playlist.controller.request.RegisterRequest;
import com.playlist.dto.UserDto;

public interface LoginService {
    UserDto login(LoginRequest loginRequest);

    String register(RegisterRequest userLogin);
}
