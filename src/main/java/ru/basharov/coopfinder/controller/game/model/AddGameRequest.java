package ru.basharov.coopfinder.controller.game.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGameRequest {
    private String title;
    private String description;
}
