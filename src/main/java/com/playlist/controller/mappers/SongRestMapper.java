package com.playlist.controller.mappers;

import com.playlist.controller.request.SongRequest;
import com.playlist.dto.Song;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SongRestMapper {

    Song requestToDomain(SongRequest request);
}
