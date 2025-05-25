package com.playlist.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
