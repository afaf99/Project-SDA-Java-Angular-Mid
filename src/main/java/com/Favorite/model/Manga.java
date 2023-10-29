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
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manga_id")
    private Integer id;

    @NotNull
    private Integer chapters;
    @NotNull
    private Integer pages;

    @ManyToOne
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;

    @Override
    public String toString() {
        return "Manga{" +
                "id=" + id +
                ", chapters=" + chapters +
                ", pages=" + pages +
                '}';
    }
}
