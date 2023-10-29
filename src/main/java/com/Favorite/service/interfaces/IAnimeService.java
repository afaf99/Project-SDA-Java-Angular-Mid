package com.Favorite.service.interfaces;

import com.Favorite.model.Anime;

import java.util.List;

public interface IAnimeService {

    public List<Anime> getAllAnime();
    public Anime getAnimeById(Integer id);
    public Anime saveAnime(Anime anime);

    void updateAnime(Anime anime, Integer id);

    void deleteAnime(Integer id);
}
