package com.playlist.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongRequest {
    private String titulo;
    private String artista;
    private String album;
    private String anno;
    private String genero;
}
