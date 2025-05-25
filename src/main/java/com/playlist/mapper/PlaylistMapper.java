package com.playlist.mapper;

import com.playlist.dto.Playlist;
import com.playlist.entity.PlaylistEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    Playlist entityToDomain(PlaylistEntity entity);
    List<Playlist> entitiesToDomains(List<PlaylistEntity> entity);
    PlaylistEntity domainToEntity(Playlist domain);

}
