package com.playlist.controller.mappers;

import com.playlist.controller.request.PlaylistRequest;
import com.playlist.dto.Playlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistRestMapper {
    Playlist requestToDomain(PlaylistRequest request);
}
