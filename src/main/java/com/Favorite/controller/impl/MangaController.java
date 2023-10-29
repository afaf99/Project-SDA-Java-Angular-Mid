package com.Favorite.controller.impl;

import com.Favorite.controller.interfaces.IMangaController;
import com.Favorite.model.Anime;
import com.Favorite.model.Manga;
import com.Favorite.repository.MangaRepository;
import com.Favorite.service.impl.MangaService;
import com.Favorite.service.interfaces.IMangaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MangaController implements IMangaController {

    @Autowired
    MangaRepository mangaRepository;

    @Autowired
    private IMangaService mangaService;


    @GetMapping("/manga")
    public List<Manga> getAllManga(){
        return mangaService.getAllManga();
    }
    @GetMapping("/manga/{id}")
    public Manga getMangaById(@PathVariable(name = "id") Integer id){
        return mangaService.getMangaById(id);
    }

    @PostMapping("/manga")
    @ResponseStatus(HttpStatus.CREATED)
    public Manga saveManga(@RequestBody @Valid Manga manga){
        return mangaService.saveManga(manga);
    }

    @PutMapping("/manga/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateManga(@RequestBody @Valid Manga manga, @PathVariable Integer id){
        mangaService.updateManga(manga, id);
    }

    @DeleteMapping("/manga/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteManga(@PathVariable Integer id){
        mangaService.deleteManga(id);
    }

}
