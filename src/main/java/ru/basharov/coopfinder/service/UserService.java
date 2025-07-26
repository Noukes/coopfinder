package ru.basharov.coopfinder.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.basharov.coopfinder.model.User;
import ru.basharov.coopfinder.exception.authentication.EmailAlreadyExistsException;
import ru.basharov.coopfinder.model.UserPreference;
import ru.basharov.coopfinder.model.common.Gender;
import ru.basharov.coopfinder.repository.UserRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        user.setPreferences(UserPreference.builder()
                .user(user)
                .gender(Gender.ANY)
                .onlyHasMic(false).build());

        user.setCreatedAt(LocalDate.now());
        userRepository.save(user);
    }

    public User getCurrentUser() {
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByEmail(email);
    }

    public User updateUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return userRepository.save(user);
        }
        throw new EmailAlreadyExistsException("Email does not exist");
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }
}
