package com.playlist.repository;

import com.playlist.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Integer> {

    Optional<PlaylistEntity> findByNombre(String name);

}