package ru.basharov.coopfinder.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.basharov.coopfinder.controller.gamerating.model.AddGameRatingRequest;
import ru.basharov.coopfinder.controller.gamerating.model.GameRatingResponse;
import ru.basharov.coopfinder.mapper.GameRatingMapper;
import ru.basharov.coopfinder.model.Game;
import ru.basharov.coopfinder.model.GameRating;
import ru.basharov.coopfinder.model.User;
import ru.basharov.coopfinder.repository.GameRatingRepository;
import ru.basharov.coopfinder.repository.GameRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameRatingService {

    private final GameRatingRepository gameRatingRepository;
    private final GameRatingMapper gameRatingMapper;
    private final UserService userService;
    private final GameRepository gameRepository;

    public Set<GameRatingResponse> getGameRatings() {
        final User currentUser = userService.getCurrentUser();
        return currentUser.getGameRatings()
                .stream()
                .map(gameRatingMapper::EntityToResponse)
                .collect(Collectors.toSet());
    }

    public GameRatingResponse addGameRating(AddGameRatingRequest request) {
        final User currentUser = userService.getCurrentUser();
        final Game game = gameRepository.findById(request.getGameId())
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));

        final GameRating gameRating = gameRatingMapper.addRequestToEntity(request);
        gameRating.setUser(currentUser);
        gameRating.setGame(game);

        return gameRatingMapper.EntityToResponse(gameRatingRepository.save(gameRating));
    }

    public void deleteGameRating(Long gameRatingId) {
        final User currentUser = userService.getCurrentUser();
        final GameRating gameRating = gameRatingRepository.findById(gameRatingId)
                .orElseThrow(() -> new EntityNotFoundException("Game rating not found"));

        if (!gameRating.getUser().equals(currentUser))
            throw new RuntimeException("You are not allowed to delete this game");

        gameRatingRepository.delete(gameRating);
    }
}
