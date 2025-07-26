package ru.basharov.coopfinder.controller.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.basharov.coopfinder.controller.profile.model.ProfileDto;
import ru.basharov.coopfinder.service.SearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping()
    public ResponseEntity<List<ProfileDto>> search() {
        return ResponseEntity.ok(searchService.searchUsers());
    }
}
