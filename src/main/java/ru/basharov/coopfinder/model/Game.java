package ru.basharov.coopfinder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.basharov.coopfinder.model.common.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;
}
