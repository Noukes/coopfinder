package ru.basharov.coopfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.basharov.coopfinder.model.GameRating;
import ru.basharov.coopfinder.model.User;

public interface GameRatingRepository extends JpaRepository<GameRating, Long> {
    public GameRating findByUserId(Long userId);

    Long user(User user);

    void deleteGameRatingByUser(User user);

}
