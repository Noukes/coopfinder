package ru.basharov.coopfinder.repository.specs;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import ru.basharov.coopfinder.model.User;
import ru.basharov.coopfinder.model.UserPreference;
import ru.basharov.coopfinder.model.common.Gender;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> searchForMatches(User currentUser) {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            UserPreference userPreference = currentUser.getPreferences();

            predicates.add(cb.notEqual(root.get("id"), currentUser.getId()));
            if (userPreference.isOnlyHasMic()) predicates.add(cb.equal(root.get("has_mic"), true));

            switch (userPreference.getGender()) {
                case MALE -> predicates.add(cb.equal(root.get("gender"), Gender.MALE));
                case FEMALE -> predicates.add(cb.equal(root.get("gender"), Gender.FEMALE));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
