package com.Favorite.controller.impl;

import com.Favorite.model.Favorite;
import com.Favorite.model.Manga;
import com.Favorite.repository.FavoriteRepository;
import com.Favorite.repository.MangaRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class MangaControllerTest {


        @Autowired
        MangaRepository mangaRepository;
        @Autowired
        FavoriteRepository favoriteRepository;

        @Autowired
        WebApplicationContext webApplicationContext;

        MockMvc mockMvc;

        ObjectMapper objectMapper = new ObjectMapper();
        Manga manga;
        Favorite favorite;

        @BeforeEach
        public void setUp(){
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

            favorite = new Favorite(1, "Test", "Shonen", null, null);
            favoriteRepository.save(favorite);

            manga = new Manga(1, 500, 2200, favorite);
            mangaRepository.save(manga);
        }

        @AfterEach
        public void tearDown(){
            favoriteRepository.deleteById(favorite.getId());
            mangaRepository.deleteAll();
        }

        @Test
        void getAllManga_validRequest_allManga() throws Exception {
            MvcResult mvcResult = mockMvc.perform(get("/api/manga"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
            System.out.println(mvcResult.getResponse().getContentAsString());

            assertTrue(mvcResult.getResponse().getContentAsString().contains("Test"));
            //assertTrue(mvcResult.getResponse().getContentAsString().contains("Attack on Titan"));
        }

        @Test
        void getMangaById_validId_correctManga() throws Exception {
            MvcResult mvcResult = mockMvc.perform(get("/api/manga/" + manga.getId()))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            assertTrue(mvcResult.getResponse().getContentAsString().contains("Test"));
        }

        @Test
        void getMangaById_invalidId_notFound() throws Exception {
            mockMvc.perform(get("/api/manga/999").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();
        }

        @Test
        void saveManga_validBody_mangaSaved() throws Exception {
            Favorite myFavorite = new Favorite(2, "Test 2", "Shonen", null, null);
            favoriteRepository.save(myFavorite);
            Manga myManga = new Manga(2, 24, 820, myFavorite);

            String body = objectMapper.writeValueAsString(myManga);

            mockMvc.perform(post("/api/manga").content(body).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            List<Manga> savedMangas = mangaRepository.findAll();
            System.out.println(savedMangas);
            assertTrue(savedMangas.toString().contains("820"));
        }

        @Test
        void updateManga_validBody_mangaUpdated() throws Exception {
            String body = objectMapper.writeValueAsString(manga);

            mockMvc.perform(put("/api/manga/" + manga.getId())
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andReturn();

            Optional<Manga> updatedManga = mangaRepository.findById(manga.getId());
            assertTrue(updatedManga.isPresent());

            assertEquals(manga.getChapters(), updatedManga.get().getChapters());
            assertEquals(manga.getPages(), updatedManga.get().getPages());
        }

        @Test
        void deleteManga_validRequest_mangaDeleted() throws Exception {
            mockMvc.perform(delete("/api/manga/" + manga.getId()))
                    .andExpect(status().isNoContent())
                    .andReturn();

            assertFalse(mangaRepository.findById(manga.getId()).isPresent());
        }

    }