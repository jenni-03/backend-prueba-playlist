package com.playlist.service;

import com.playlist.dto.User;

public interface UserService {
    User getUserByEmail(String email);

    void saveUser(User user);
}
