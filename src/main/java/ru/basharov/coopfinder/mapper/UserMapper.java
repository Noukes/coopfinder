package ru.basharov.coopfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.basharov.coopfinder.controller.profile.model.ProfileDto;
import ru.basharov.coopfinder.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ProfileDto userToProfileDto(User user);

    List<ProfileDto> userToProfileDtoList(List<User> users);

    void updateUserFromProfileDto(ProfileDto profileDto, @MappingTarget User user);
}
