package com.playlist.implement;

import com.playlist.dto.Playlist;
import com.playlist.entity.PlaylistEntity;
import com.playlist.entity.SongEntity;
import com.playlist.exceptions.BusinessException;
import com.playlist.exceptions.ResourceNotFoundException;
import com.playlist.mapper.PlaylistMapper;
import com.playlist.repository.PlaylistRepository;
import com.playlist.repository.SongRepository;
import com.playlist.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PlaylistImpl implements PlaylistService {
    PlaylistRepository playlistRepository;
    SongRepository songRepository;
    PlaylistMapper playlistMapper;

    @Override
    public List<Playlist> findAll() {
        return playlistMapper.entitiesToDomains(playlistRepository.findAll());
    }

    @Override
    public Playlist create(Playlist playlist) {

        Optional<PlaylistEntity> playlistRepetida = playlistRepository.findByNombre(playlist.getNombre());

        if(playlistRepetida.isPresent()) {
            throw new BusinessException("playlist.message.playlist.repeat", playlist.getNombre());
        }

        PlaylistEntity playlistEntity = playlistMapper.domainToEntity(playlist);

        List<SongEntity> canciones = playlist.getCanciones().stream()
                .map(c -> {

                    if(c.getTitulo() == null) {
                        throw new ResourceNotFoundException("playlist.message.song.notfound");
                    }

                    Optional<SongEntity> cancionExistente = songRepository.findByTitulo(c.getTitulo());

                    if(cancionExistente.isEmpty()) {
                        throw new ResourceNotFoundException("playlist.message.song.notfound", c.getTitulo());
                    }
                    return cancionExistente.get();
                })
                .collect(Collectors.toList());

        playlistEntity.setCanciones(canciones);

        return playlistMapper.entityToDomain(playlistRepository.save(playlistEntity));
    }

    @Override
    public Playlist findByName(String name) {

        Optional<PlaylistEntity> playlist = playlistRepository.findByNombre(name);

        if(playlist.isEmpty()) {
            throw new ResourceNotFoundException("playlist.message.playlist.notfound", name);
        }

        return playlistMapper.entityToDomain(playlist.get());
    }

    @Override
    public void delete(String name) {

        Optional<PlaylistEntity> playlist = playlistRepository.findByNombre(name);

        if(playlist.isEmpty()) {
            throw new ResourceNotFoundException("playlist.message.playlist.notfound", name);
        }

        playlist.get().getCanciones().forEach(song -> song.getPlaylists().remove(playlist.get()));
        playlist.get().getCanciones().clear();


        playlistRepository.delete(playlist.get());

    }
}
