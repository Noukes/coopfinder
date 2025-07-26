package ru.basharov.coopfinder.mapper;

import org.mapstruct.Mapper;
import ru.basharov.coopfinder.controller.preferences.model.UserPreferenceDto;
import ru.basharov.coopfinder.model.UserPreference;

@Mapper(componentModel = "spring")
public interface UserPreferenceMapper {
    public UserPreferenceDto entityToDto(UserPreference preference);

    public UserPreference dtoToEntity(UserPreferenceDto dto);
}
