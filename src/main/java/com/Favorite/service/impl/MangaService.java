package com.Favorite.service.impl;

import com.Favorite.model.Anime;
import com.Favorite.model.Manga;
import com.Favorite.repository.MangaRepository;
import com.Favorite.service.interfaces.IMangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MangaService implements IMangaService {

    @Autowired
    MangaRepository mangaRepository;

    public List<Manga> getAllManga(){
        return mangaRepository.findAll();
    }

    public Manga getMangaById(Integer id){
        Optional<Manga> mangaOptional = mangaRepository.findById(id);
        if (mangaOptional.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Manga id " + id + " is not found");
        return mangaOptional.get();
    }

    public Manga saveManga(Manga manga){
        return mangaRepository.save(manga);
    }

    @Override
    public void updateManga(Manga manga, Integer id) {
        Optional<Manga> mangaOptional = mangaRepository.findById(id);
        if (mangaOptional.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Manga id " + id + " is not found");
        manga.setId(id);
        mangaRepository.save(manga);
    }

    @Override
    public void deleteManga(Integer id) {
        Optional<Manga> mangaOptional = mangaRepository.findById(id);
        if (mangaOptional.isEmpty())throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Manga id " + id + " is not found");
        mangaRepository.deleteById(id);
    }
}
