package orm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import orm.dto.UserAccountDto;
import orm.repository.UserAccountRepository;
import orm.security.AuthService;
import orm.security.LoginRequest;
import orm.security.RegistrationRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public UserAccountDto login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
    @PostMapping("/register")
    public UserAccountDto register(@RequestBody RegistrationRequest request) {
        return service.register(request);
    }
}
