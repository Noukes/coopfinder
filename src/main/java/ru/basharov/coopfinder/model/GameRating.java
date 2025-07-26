package ru.basharov.coopfinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.basharov.coopfinder.model.common.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "game_ratings")
public class GameRating extends BaseEntity {

    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToOne()
    @JoinColumn(nullable = false)
    private Game game;
}
