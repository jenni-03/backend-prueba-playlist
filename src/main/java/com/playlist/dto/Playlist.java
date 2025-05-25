package com.playlist.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Playlist {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Song> canciones;
}