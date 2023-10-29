package com.Favorite.service.impl;

import com.Favorite.model.Anime;
import com.Favorite.repository.AnimeRepository;
import com.Favorite.service.interfaces.IAnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService implements IAnimeService {

    @Autowired
    AnimeRepository animeRepository;
    @Override
    public List<Anime> getAllAnime(){
        return animeRepository.findAll();
    }
    @Override
    public Anime getAnimeById(Integer id){
        Optional<Anime> animeOptional = animeRepository.findById(id);
        if (animeOptional.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime id " + id + " is not found");
        return animeOptional.get();
    }
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public Anime saveAnime(Anime anime){
        animeRepository.save(anime);
        return anime;
    }

    @Override
    public void updateAnime(Anime anime, Integer id) {
        Optional<Anime> animeOptional = animeRepository.findById(id);
        if (animeOptional.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime id " + id + " is not found");
        anime.setId(id);
        animeRepository.save(anime);
    }

    @Override
    public void deleteAnime(Integer id) {
        Optional<Anime> animeOptional = animeRepository.findById(id);
        if (animeOptional.isEmpty())throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime id " + id + " is not found");
        animeRepository.deleteById(id);
    }
}
