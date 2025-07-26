package ru.basharov.coopfinder.mapper;

import org.mapstruct.Mapper;
import ru.basharov.coopfinder.controller.game.model.AddGameRequest;
import ru.basharov.coopfinder.controller.game.model.GameResponse;
import ru.basharov.coopfinder.model.Game;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameResponse EntityToResponse(Game game);

    Game addRequestToGame(AddGameRequest request);

}
