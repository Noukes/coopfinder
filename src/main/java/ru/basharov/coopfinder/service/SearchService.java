package ru.basharov.coopfinder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.basharov.coopfinder.controller.profile.model.ProfileDto;
import ru.basharov.coopfinder.mapper.UserMapper;
import ru.basharov.coopfinder.model.GameRating;
import ru.basharov.coopfinder.model.User;
import ru.basharov.coopfinder.repository.UserRepository;
import ru.basharov.coopfinder.repository.specs.UserSpecification;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<ProfileDto> searchUsers() {
        final User currentUser = userService.getCurrentUser();
        List<User> users = userRepository.findAll(UserSpecification.searchForMatches(currentUser));
        return userMapper.userToProfileDtoList(sortUsers(users));
    }

    private List<User> sortUsers(List<User> users) {
        final User currentUser = userService.getCurrentUser();

        Map<Long, Integer> currentRatings = currentUser.getGameRatings().stream()
                .collect(Collectors.toMap(
                        gr -> gr.getGame().getId(),
                        GameRating::getRating
                ));

        return users.stream()
                .sorted(Comparator.comparingInt(user -> {
                    List<GameRating> otherRatings = List.copyOf(user.getGameRatings());

                    return otherRatings.stream()
                            .filter(r -> currentRatings.containsKey(r.getGame().getId()))
                            .mapToInt(r -> Math.abs(r.getRating() - currentRatings.get(r.getGame().getId())))
                            .sum();
                }))
                .collect(Collectors.toList());
    }

}
