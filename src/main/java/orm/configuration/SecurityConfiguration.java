package orm.configuration;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import orm.exception.NoSuchEntityFieldException;
import orm.model.UserAccount;
import orm.repository.UserAccountRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserAccountRepository repository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auth/**")
                        .permitAll()
                        .requestMatchers("/api/students/get/**",
                                "/api/students/get-all/**",
                                "/api/groups/get/**",
                                "/api/groups/get-all")
                        .hasAnyRole("USER", "ADMINISTRATOR")
                        .requestMatchers("/api/students/create/**",
                                "/api/students/update/**",
                                "/api/students/delete/**",
                                "/api/groups/create",
                                "/api/groups/update/**",
                                "/api/groups/delete/**")
                        .hasRole("ADMINISTRATOR")
                        .anyRequest().authenticated())
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findUserAccountByUsername(username)
                .orElseThrow(() -> {
                            throw new NoSuchEntityFieldException(UserAccount.class.getSimpleName(), "Username", username);
                        }
                );
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
