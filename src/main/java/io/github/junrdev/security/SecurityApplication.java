package io.github.junrdev.security;

import io.github.junrdev.security.entity.UserInfo;
import io.github.junrdev.security.repo.UserInfoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner{

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityApplication.class);

    @Autowired
    private UserInfoRepo infoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        LOGGER.info("Command Line Runner");

        var user = infoRepo.save(UserInfo.builder()
                .name("admin")
                .password(passwordEncoder.encode("admin01"))
                .roles("ROLE_ADMIN")
                .email("a@b.c")
                .build()
        );

        LOGGER.info("Saved " + user);
    }
}
