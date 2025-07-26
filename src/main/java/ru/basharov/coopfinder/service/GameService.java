package ru.basharov.coopfinder.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.basharov.coopfinder.controller.game.model.AddGameRequest;
import ru.basharov.coopfinder.controller.game.model.GameResponse;
import ru.basharov.coopfinder.mapper.GameMapper;
import ru.basharov.coopfinder.model.Game;
import ru.basharov.coopfinder.repository.GameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameResponse getGameById(Long id) {
        final Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));
        return gameMapper.EntityToResponse(game);
    }

    public List<GameResponse> getGames() {
        return gameRepository
                .findAll().stream()
                .map(gameMapper::EntityToResponse)
                .collect(Collectors.toList());
    }

    public GameResponse addGame(AddGameRequest request) {
        final Game game = gameMapper.addRequestToGame(request);
        final Game savedGame = gameRepository.save(game);
        return gameMapper.EntityToResponse(savedGame);
    }

    public void deleteGame(Long gameId) {
        gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game not found"));
        gameRepository.deleteById(gameId);
    }

}
