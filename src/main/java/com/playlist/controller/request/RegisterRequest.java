package com.playlist.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;
}
