package com.Favorite.service.interfaces;

import com.Favorite.model.Manga;

import java.util.List;

public interface IMangaService {


    public List<Manga> getAllManga();
    public Manga getMangaById(Integer id);
    public Manga saveManga(Manga manga);
    public void updateManga(Manga manga, Integer id);

    void deleteManga(Integer id);
}
