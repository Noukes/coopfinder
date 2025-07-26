package ru.basharov.coopfinder.controller.gamerating.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGameRatingRequest {
    private int rating;
    private Long gameId;
}
