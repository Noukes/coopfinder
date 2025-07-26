package ru.basharov.coopfinder.controller.profile.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.basharov.coopfinder.model.common.Gender;

import java.util.Date;

@Getter
@Setter
@Builder
public class ProfileDto {
    private String name;
    private String email;
    private String about;
    private Date dateOfBirth;
    private Gender gender;
    private boolean hasMic;
}
