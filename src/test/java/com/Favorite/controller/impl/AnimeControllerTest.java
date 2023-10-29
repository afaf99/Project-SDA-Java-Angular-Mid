package com.Favorite.controller.impl;

import com.Favorite.model.Anime;
import com.Favorite.model.Favorite;
import com.Favorite.repository.AnimeRepository;
import com.Favorite.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class AnimeControllerTest {

    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    Anime anime;
    Favorite favorite;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        favorite = new Favorite(1, "Test", "Shonen", null, null);
        favoriteRepository.save(favorite);

        anime = new Anime(1, 24, 220, favorite);
        animeRepository.save(anime);
    }

    @AfterEach
    public void tearDown(){
        favoriteRepository.deleteById(favorite.getId());
        animeRepository.deleteAll();
    }

    @Test
    void getAllAnime_validRequest_allAnime() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/anime"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test"));
        //assertTrue(mvcResult.getResponse().getContentAsString().contains("Attack on Titan"));
    }

    @Test
    void getAnimeById_validId_correctAnime() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/anime/" + anime.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test"));
    }

    @Test
    void getAnimeById_invalidId_notFound() throws Exception {
        mockMvc.perform(get("/api/anime/999").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void saveAnime_validBody_animeSaved() throws Exception {
        Favorite myFavorite = new Favorite(2, "Test 2", "Shonen", null, null);
        favoriteRepository.save(myFavorite);
        Anime myAnime = new Anime(2, 24, 820, myFavorite);

        String body = objectMapper.writeValueAsString(myAnime);

        mockMvc.perform(post("/api/anime").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        List<Anime> savedAnimes = animeRepository.findAll();
        System.out.println(savedAnimes);
        assertTrue(savedAnimes.toString().contains("820"));
    }

    @Test
    void updateAnime_validBody_animeUpdated() throws Exception {
        String body = objectMapper.writeValueAsString(anime);

        mockMvc.perform(put("/api/anime/" + anime.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Anime> updatedAnime = animeRepository.findById(anime.getId());
        assertTrue(updatedAnime.isPresent());

        assertEquals(anime.getDuration(), updatedAnime.get().getDuration());
        assertEquals(anime.getEpisode(), updatedAnime.get().getEpisode());
    }

    @Test
    void deleteAnime_validRequest_animeDeleted() throws Exception {
        mockMvc.perform(delete("/api/anime/" + anime.getId()))
                .andExpect(status().isNoContent())
                .andReturn();

        assertFalse(animeRepository.findById(anime.getId()).isPresent());
    }

}