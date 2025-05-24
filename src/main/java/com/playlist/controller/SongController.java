package com.playlist.controller;

import com.playlist.dto.Song;
import com.playlist.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;


    @GetMapping
    public ResponseEntity<List<Song>> getSongs() {
        List<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Song> createSong(@Validated @RequestBody Song songRequest) {

        Song song = songService.create(songRequest);

        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }
}
