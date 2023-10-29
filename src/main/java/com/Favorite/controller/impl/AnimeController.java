package com.Favorite.controller.impl;

import com.Favorite.controller.interfaces.IAnimeController;
import com.Favorite.model.Anime;
import com.Favorite.repository.AnimeRepository;
import com.Favorite.service.interfaces.IAnimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AnimeController implements IAnimeController {

    @Autowired
    AnimeRepository animeRepository;

    @Autowired
    IAnimeService animeService;


    @GetMapping("/anime")
    @ResponseStatus(HttpStatus.OK)
    public List<Anime> getAllAnime(){
        return animeService.getAllAnime();
    }

    @GetMapping("/anime/{id}")
    public Anime getAnimeById(@PathVariable(name = "id") Integer id){
        return animeService.getAnimeById(id);
    }

    @PostMapping("/anime")
    @ResponseStatus(HttpStatus.CREATED)
    public Anime saveAnime(@RequestBody @Valid Anime anime){
        return animeService.saveAnime(anime);
    }

    @PutMapping("/anime/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnime(@RequestBody @Valid Anime anime, @PathVariable Integer id){
        animeService.updateAnime(anime, id);
    }

    @DeleteMapping("/anime/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnime(@PathVariable Integer id){
        animeService.deleteAnime(id);
    }

}
