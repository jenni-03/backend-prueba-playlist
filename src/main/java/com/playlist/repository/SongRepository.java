package com.playlist.repository;

import com.playlist.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {
    Optional<SongEntity> findByTitulo(String title);

}
