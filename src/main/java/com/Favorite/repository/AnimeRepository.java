package com.Favorite.repository;

import com.Favorite.model.Anime;
import com.Favorite.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {

    List<Anime> findAll();
    Optional<Anime> findById(Integer id);


}
