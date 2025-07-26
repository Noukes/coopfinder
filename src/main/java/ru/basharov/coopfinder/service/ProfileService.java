package ru.basharov.coopfinder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.basharov.coopfinder.controller.profile.model.ProfileDto;
import ru.basharov.coopfinder.mapper.UserMapper;
import ru.basharov.coopfinder.model.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final UserService userService;
    private final UserMapper userMapper;

    public ProfileDto getProfile() {
        return userMapper.userToProfileDto(userService.getCurrentUser());
    }

    public ProfileDto updateProfile(ProfileDto profileDto) {
        final User user = userService.getCurrentUser();
        userMapper.updateUserFromProfileDto(profileDto, user);
        return userMapper.userToProfileDto(userService.updateUser(user));
    }
}
