package io.github.junrdev.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // authentication
    @Bean
    public UserDetailsService userDetailsService(){

//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("admin01"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(encoder.encode("user01"))
//                .roles("USER")
//                .build();
//
//        // since we are using local storage instead of db
//        return new InMemoryUserDetailsManager(admin, user);

        return new UserInfoUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // authorisation
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/welcome/**")
                .permitAll()// access to all

                .and()
                .authorizeHttpRequests().requestMatchers("/products/**", "/admin/**", "/user/**")
                .authenticated()// only authenticated users
                .and()
                .formLogin()

                .and()
                .build();
    }

    // bean required for Username password authentication
    @Bean
    public AuthenticationProvider authenticationProvider(){
        // pass the UserDetailsService and the Password Encoder
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }
}
