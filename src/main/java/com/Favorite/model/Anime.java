package com.Favorite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anime_id")
    private Integer id;
    @NotNull
    private Integer duration;
    @NotNull
    private Integer episode;

    @ManyToOne
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", duration=" + duration +
                ", episode=" + episode +
                '}';
    }
}
