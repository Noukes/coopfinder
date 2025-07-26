package ru.basharov.coopfinder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.basharov.coopfinder.controller.authentication.model.AuthFormRequest;
import ru.basharov.coopfinder.controller.authentication.model.JwtTokenResponse;
import ru.basharov.coopfinder.model.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtTokenResponse signUp(AuthFormRequest authFormRequest) {
        final User user = new User();
        user.setEmail(authFormRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authFormRequest.getPassword()));

        userService.createUser(user);

        return new JwtTokenResponse(jwtService.generateToken(user));
    }

    public JwtTokenResponse signIn(AuthFormRequest authFormRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authFormRequest.getEmail(), authFormRequest.getPassword()
        ));

        final UserDetails userDetails = userService
                .userDetailsService()
                .loadUserByUsername(authFormRequest.getEmail());

        return new JwtTokenResponse(jwtService.generateToken(userDetails));
    }

}
