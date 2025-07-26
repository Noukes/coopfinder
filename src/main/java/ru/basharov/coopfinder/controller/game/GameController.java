package ru.basharov.coopfinder.controller.game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.basharov.coopfinder.controller.game.model.AddGameRequest;
import ru.basharov.coopfinder.controller.game.model.GameResponse;
import ru.basharov.coopfinder.service.GameService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGame(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getGames() {
        return ResponseEntity.ok(gameService.getGames());
    }

    @PostMapping("/add")
    public ResponseEntity<GameResponse> addGame(@RequestBody AddGameRequest request) {
        return ResponseEntity.ok(gameService.addGame(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
