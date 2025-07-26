package ru.basharov.coopfinder.controller.game.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameResponse {
    private Long id;
    private String title;
    private String description;
}
