package com.playlist.implement;

import com.playlist.dto.Song;
import com.playlist.entity.SongEntity;
import com.playlist.mapper.SongMapper;
import com.playlist.repository.SongRepository;
import com.playlist.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class SongImpl implements SongService {

    SongRepository songRepository;
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

}
