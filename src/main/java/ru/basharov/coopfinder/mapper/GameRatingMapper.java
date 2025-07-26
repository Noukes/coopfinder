package ru.basharov.coopfinder.mapper;

import org.mapstruct.Mapper;
import ru.basharov.coopfinder.controller.gamerating.model.AddGameRatingRequest;
import ru.basharov.coopfinder.controller.gamerating.model.GameRatingResponse;
import ru.basharov.coopfinder.model.GameRating;

@Mapper(componentModel = "spring")
public interface GameRatingMapper {

    GameRatingResponse EntityToResponse(GameRating gameRating);

    GameRating addRequestToEntity(AddGameRatingRequest request);
}
