package orm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import orm.dto.UserAccountDto;
import orm.exception.NoSuchEntityFieldException;
import orm.model.UserAccount;
import orm.model.enums.UserRole;
import orm.repository.UserAccountRepository;

@Service
public class AuthService {

    @Autowired
    private UserAccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserAccountDto register(RegistrationRequest request) {
        UserAccount account = new UserAccount();
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole(request.getRole());
        return UserAccountDto.fromEntity(repository.save(account));
    }

    public UserAccountDto login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserAccount account = repository.findUserAccountByUsername(
                request.getUsername()).orElseThrow(() -> {
                    throw new NoSuchEntityFieldException(UserAccount.class.getSimpleName(), "Username", request.getUsername());
                }
        );
        return UserAccountDto.fromEntity(account);
    }


}
