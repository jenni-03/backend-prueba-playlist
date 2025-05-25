package com.playlist.controller;

import com.playlist.dto.Playlist;
import com.playlist.service.PlaylistService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lists")
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylist() {
        return new ResponseEntity<>(playlistService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody @Valid Playlist playlistRequest) {
        return new ResponseEntity<>(playlistService.create(playlistRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{listName}")
    public ResponseEntity<Playlist> getPlaylistByName(@PathVariable @NotNull String listName) {
        Playlist domain = playlistService.findByName(listName);
        return new ResponseEntity<>((domain), HttpStatus.OK);

    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<Void> deletePlaylistByName(@PathVariable @NotNull String listName) {
        playlistService.delete(listName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
