package ru.basharov.coopfinder.controller.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.basharov.coopfinder.controller.profile.model.ProfileDto;
import ru.basharov.coopfinder.service.ProfileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping()
    public ResponseEntity<ProfileDto> getProfile() {
        return ResponseEntity.ok(profileService.getProfile());
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody @Validated ProfileDto request) {
        return ResponseEntity.ok(profileService.updateProfile(request));
    }
}
