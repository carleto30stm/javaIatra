package medic.iatro.api.controller;

import jakarta.validation.Valid;
import medic.iatro.api.domain.users.DataLogin;
import medic.iatro.api.domain.users.User;
import medic.iatro.api.domain.users.repository.UserRepository;
import medic.iatro.api.infra.security.DataJWT;
import medic.iatro.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataLogin dataLogin) {
        try {
            Authentication AuthToken = new UsernamePasswordAuthenticationToken(dataLogin.username(), dataLogin.password());
           var authenticatedUser = authenticationManager.authenticate(AuthToken);
            var JWTtoken  = tokenService.generateJWT((User) authenticatedUser.getPrincipal());
            return ResponseEntity.ok(new DataJWT(JWTtoken));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Fallo de autenticación");
        }
    }

    @PostMapping("/create")
    public void create(@RequestBody User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
         user.setPassword(hashedPassword);
        userRepository.save(user);
        System.out.println(user.getUsername());
    }

}
