package com.Favorite.repository;

import com.Favorite.model.Favorite;
import com.Favorite.model.Manga;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MangaRepositoryTest {

    @Autowired
    MangaRepository mangaRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    Manga manga;

    @BeforeEach
    public void setUp() {
        Favorite favorite = new Favorite();
        favorite.setTitle("Made in Abyss");
        favorite.setGenre("Adventure");
        favoriteRepository.save(favorite);

        manga = new Manga();
        manga.setChapters(60);
        manga.setPages(1500);
        manga.setFavorite(favorite);
        mangaRepository.save(manga);
    }

    @AfterEach
    public void tearDown() {
        mangaRepository.deleteAll();
        favoriteRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<Manga> manga = mangaRepository.findAll();

        assertEquals(1, manga.size());
        assertTrue(manga.contains(manga.get(0)));
    }

    @Test
    void findById() {
        Optional<Manga> mangaOptional = mangaRepository.findById(manga.getId());

        assertTrue(mangaOptional.isPresent());
        assertEquals(manga.getId(), mangaOptional.get().getId());

    }
}