package ru.basharov.coopfinder.controller.preferences;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.basharov.coopfinder.controller.preferences.model.UserPreferenceDto;
import ru.basharov.coopfinder.service.UserPreferenceService;

@RestController
@RequestMapping("/api/v1/profile/preferences")
@RequiredArgsConstructor
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;

    @GetMapping()
    private ResponseEntity<UserPreferenceDto> getPreferences() {
        return ResponseEntity.ok(userPreferenceService.getPreferences());
    }

    @PutMapping("/update")
    private ResponseEntity<UserPreferenceDto> updatePreferences(@RequestBody UserPreferenceDto request) {
        return ResponseEntity.ok(userPreferenceService.updatePreferences(request));
    }

    @PutMapping("clear-preferences")
    private ResponseEntity<UserPreferenceDto> clearPreferences() {
        return ResponseEntity.ok(userPreferenceService.clearPreferences());
    }
}
