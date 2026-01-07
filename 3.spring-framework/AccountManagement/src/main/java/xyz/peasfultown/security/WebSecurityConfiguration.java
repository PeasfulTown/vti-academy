package xyz.peasfultown.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import xyz.peasfultown.services.IAccountService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {
    private IAccountService accountService;

    @Autowired
    public WebSecurityConfiguration(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> {

                })
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/v1/login/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/accounts/**").hasRole(
                                        "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/accounts/**").hasRole(
                                        "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/accounts/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/accounts/**").hasAnyRole("ADMIN", "USER")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(accountService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
