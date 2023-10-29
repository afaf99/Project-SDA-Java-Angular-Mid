package com.Favorite.repository;

import com.Favorite.model.Anime;
import com.Favorite.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

//    Optional<Favorite> findById(Integer id);
}
