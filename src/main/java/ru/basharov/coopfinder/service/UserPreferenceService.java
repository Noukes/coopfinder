package ru.basharov.coopfinder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.basharov.coopfinder.controller.preferences.model.UserPreferenceDto;
import ru.basharov.coopfinder.mapper.UserPreferenceMapper;
import ru.basharov.coopfinder.model.User;
import ru.basharov.coopfinder.model.UserPreference;
import ru.basharov.coopfinder.model.common.Gender;
import ru.basharov.coopfinder.repository.UserPreferenceRepository;

@Service
@RequiredArgsConstructor
public class UserPreferenceService {

    private final UserPreferenceRepository userPreferenceRepository;
    private final UserPreferenceMapper userPreferenceMapper;
    private final UserService userService;


    public UserPreferenceDto getPreferences() {
        final User currentUser = userService.getCurrentUser();
        return userPreferenceMapper.entityToDto(userPreferenceRepository
                .findById(currentUser.getId())
                .orElse(UserPreference.builder()
                        .gender(Gender.ANY)
                        .onlyHasMic(false).build()));
    }

    public UserPreferenceDto updatePreferences(UserPreferenceDto request) {
        final User currentUser = userService.getCurrentUser();
        final UserPreference userPreference = UserPreference.builder()
                .id(currentUser.getId())
                .user(currentUser)
                .gender(request.getGender())
                .onlyHasMic(request.isOnlyHasMic())
                .build();
        final UserPreference savedUserPreference = userPreferenceRepository.save(userPreference);
        return userPreferenceMapper.entityToDto(savedUserPreference);
    }

    public UserPreferenceDto clearPreferences() {
        final User currentUser = userService.getCurrentUser();
        return userPreferenceMapper.entityToDto(userPreferenceRepository.save(
                        UserPreference.builder()
                                .id(currentUser.getId())
                                .user(currentUser)
                                .gender(Gender.ANY)
                                .onlyHasMic(false)
                                .build()
                )
        );
    }
}
