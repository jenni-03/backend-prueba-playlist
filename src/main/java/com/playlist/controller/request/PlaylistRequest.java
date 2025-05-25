package com.playlist.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PlaylistRequest {
    @NotNull
    private String nombre;
    @NotNull
    private String descripcion;
    private List<@Valid SongRequest> canciones;
}
