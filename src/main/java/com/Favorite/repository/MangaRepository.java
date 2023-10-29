package com.Favorite.repository;

import com.Favorite.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer> {

    List<Manga> findAll();
    Optional<Manga> findById(Integer id);
}
