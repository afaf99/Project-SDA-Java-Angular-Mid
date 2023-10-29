package com.Favorite.repository;

import com.Favorite.model.Anime;
import com.Favorite.model.Favorite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AnimeRepositoryTest {

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    Anime anime;

    @BeforeEach
    public void setUp() {
        Favorite favorite = new Favorite();
        favorite.setTitle("Bleach");
        favorite.setGenre("Action");
        favoriteRepository.save(favorite);

        anime = new Anime();
        anime.setDuration(22);
        anime.setEpisode(500);
        anime.setFavorite(favorite);
        animeRepository.save(anime);
    }


    @AfterEach
    public void tearDown(){
        animeRepository.deleteAll();
        favoriteRepository.deleteAll();;
    }

    @Test
    void findAll() {
        List<Anime> anime = animeRepository.findAll();

        assertEquals(1, anime.size());
        assertTrue(anime.contains(anime.get(0)));
    }

    @Test
    void findById() {
        Optional<Anime> animeOptional = animeRepository.findById(anime.getId());

        assertTrue(animeOptional.isPresent());
        assertEquals(anime.getId(), animeOptional.get().getId());

    }
}