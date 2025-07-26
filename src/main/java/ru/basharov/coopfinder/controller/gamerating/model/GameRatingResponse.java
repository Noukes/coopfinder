package ru.basharov.coopfinder.controller.gamerating.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GameRatingResponse {
    private int id;
    private int rating;
    private int gameId;
}
