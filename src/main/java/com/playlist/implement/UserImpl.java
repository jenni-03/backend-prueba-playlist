package com.playlist.implement;

import com.playlist.dto.User;
import com.playlist.entity.UserEntity;
import com.playlist.exceptions.BusinessException;
import com.playlist.exceptions.ResourceNotFoundException;
import com.playlist.mapper.UserMapper;
import com.playlist.repository.UserRepository;
import com.playlist.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if(userEntity.isEmpty()) {
            throw new ResourceNotFoundException("playlist.message.user.notfound", email);
        }

        return userMapper.entityToDomain(userEntity.get());
    }

    @Override
    public void saveUser(User user) {
        Optional<UserEntity> entity = userRepository.findByEmail(user.getEmail());

        if(entity.isPresent()) {
            throw new BusinessException("playlist.message.user.repeat", user.getEmail());
        }

        UserEntity userEntity = userMapper.domainToEntity(user);

        userRepository.save(userEntity);
    }
}
