package ru.basharov.coopfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.basharov.coopfinder.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
