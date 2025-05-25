package com.playlist.implement;

import com.playlist.dto.Song;
import com.playlist.entity.PlaylistEntity;
import com.playlist.entity.SongEntity;
import com.playlist.exceptions.BusinessException;
import com.playlist.exceptions.ResourceNotFoundException;
import com.playlist.mapper.SongMapper;
import com.playlist.repository.PlaylistRepository;
import com.playlist.repository.SongRepository;
import com.playlist.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class SongImpl implements SongService {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    SongMapper songMapper;

    @Override
    public List<Song> findAll() {

        List<SongEntity> list = songRepository.findAll();
        return songMapper.entitiesToDomains(list);
    }

    @Override
    public Song create(Song song) {
        SongEntity songEntity = songMapper.domainToEntity(song);
        return songMapper.entityToDomain(songRepository.save(songEntity));
    }

    @Override
    public String addSongToPlaylist(String playlistName, Long songId) {

        Optional<SongEntity> song = songRepository.findById(songId);

        if(song.isEmpty()) {
            throw new ResourceNotFoundException("playlist.message.song.notfound", songId);
        }

        Optional<PlaylistEntity> playlist = playlistRepository.findByNombre(playlistName);

        if(playlist.isEmpty()) {
            throw new ResourceNotFoundException("playlist.message.playlist.notfound", playlistName);
        }

        if (!playlist.get().getCanciones().contains(song.get())) {
            playlist.get().getCanciones().add(song.get());
            playlistRepository.save(playlist.get());
        } else {
            throw new BusinessException("playlist.message.playlist.alreadyexists", song.get().getTitulo());
        }

        return "Canción agregada a la playlist con exito";
    }

}
