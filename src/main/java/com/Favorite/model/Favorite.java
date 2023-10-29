package com.Favorite.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Integer id;

    @NotEmpty
    private String title;
    @NotEmpty
    private String genre;

    @OneToMany(mappedBy = "favorite", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Anime> animeList = new ArrayList<>();
    @OneToMany(mappedBy = "favorite", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Manga> mangaList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
