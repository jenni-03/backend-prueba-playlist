package com.playlist.controller;

import com.playlist.controller.mappers.SongRestMapper;
import com.playlist.controller.request.SongRequest;
import com.playlist.dto.Song;
import com.playlist.service.SongService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final SongRestMapper songRestMapper;


    @GetMapping
    public ResponseEntity<List<Song>> getSongs() {
        List<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Song> createSong(@Valid @RequestBody SongRequest songRequest) {

        Song song = songService.create(songRestMapper.requestToDomain(songRequest));

        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> addSongToPlaylist(@RequestParam @NotNull String playlistName, @RequestParam @NotNull Long songId) {

        String description = songService.addSongToPlaylist(playlistName, songId);

        return new ResponseEntity<>(description, HttpStatus.OK);

    }
}
