package com.playlist.mapper;

import com.playlist.dto.User;
import com.playlist.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User entityToDomain(UserEntity entity);
    UserEntity domainToEntity(User domain);
}
