package com.playlist.controller;

import com.playlist.controller.mappers.PlaylistRestMapper;
import com.playlist.controller.request.PlaylistRequest;
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
    private final PlaylistRestMapper playlistRestMapper;

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylist() {
        return new ResponseEntity<>(playlistService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody @Valid PlaylistRequest playlistRequest) {
        return new ResponseEntity<>(playlistService.create(playlistRestMapper.requestToDomain(playlistRequest)), HttpStatus.CREATED);
    }

    @GetMapping("/{playlistName}")
    public ResponseEntity<Playlist> getPlaylistByName(@PathVariable @NotNull String playlistName) {
        Playlist domain = playlistService.findByName(playlistName);
        return new ResponseEntity<>((domain), HttpStatus.OK);

    }

    @DeleteMapping("/{playlistName}")
    public ResponseEntity<Void> deletePlaylistByName(@PathVariable @NotNull String playlistName) {
        playlistService.delete(playlistName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
