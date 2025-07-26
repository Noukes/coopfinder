package ru.basharov.coopfinder.controller.preferences.model;

import lombok.Getter;
import lombok.Setter;
import ru.basharov.coopfinder.model.common.Gender;

@Getter
@Setter
public class UserPreferenceDto {
    private Gender gender;
    private boolean onlyHasMic;
}
