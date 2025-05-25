package com.playlist.mapper;

import com.playlist.controller.request.RegisterRequest;
import com.playlist.dto.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    User requestToDomain(RegisterRequest request);
}
