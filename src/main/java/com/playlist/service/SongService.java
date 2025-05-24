package com.playlist.service;

import com.playlist.dto.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {
    List<Song> findAll();

    Song create(Song playlist);

}
