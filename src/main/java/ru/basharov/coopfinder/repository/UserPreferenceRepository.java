package ru.basharov.coopfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.basharov.coopfinder.model.UserPreference;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
}
