package com.playlist.service;

import com.playlist.dto.Playlist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaylistService {

    List<Playlist> findAll();

    Playlist create(Playlist playlist);

    Playlist findByName(String name);

    void delete(String name);

}
