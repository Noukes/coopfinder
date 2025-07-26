package ru.basharov.coopfinder.controller.gamerating;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.basharov.coopfinder.controller.gamerating.model.AddGameRatingRequest;
import ru.basharov.coopfinder.controller.gamerating.model.GameRatingResponse;
import ru.basharov.coopfinder.service.GameRatingService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/profile/game-ratings")
@RequiredArgsConstructor
public class GameRatingController {

    private final GameRatingService gameRatingService;

    @GetMapping
    public ResponseEntity<Set<GameRatingResponse>> getGameRating() {
        return ResponseEntity.ok(gameRatingService.getGameRatings());
    }

    @PutMapping("/add")
    public ResponseEntity<GameRatingResponse> addGameRating(@RequestBody AddGameRatingRequest request) {
        return ResponseEntity.ok(gameRatingService.addGameRating(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGameRating(@PathVariable Long id) {
        gameRatingService.deleteGameRating(id);
        return ResponseEntity.noContent().build();
    }
}
