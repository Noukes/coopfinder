package ru.basharov.coopfinder.controller.authentication;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.basharov.coopfinder.controller.authentication.model.AuthFormRequest;
import ru.basharov.coopfinder.controller.authentication.model.JwtTokenResponse;
import ru.basharov.coopfinder.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenResponse> signIn(@RequestBody AuthFormRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));

    }


    @PostMapping("/sign-up")
    public ResponseEntity<JwtTokenResponse> signUp(@RequestBody AuthFormRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }
}
