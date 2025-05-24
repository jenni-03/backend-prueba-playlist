package com.playlist.mapper;

import com.playlist.dto.Song;
import com.playlist.entity.SongEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song entityToDomain(SongEntity entity);
    List<Song> entitiesToDomains(List<SongEntity> entity);
    SongEntity domainToEntity(Song domain);
}
