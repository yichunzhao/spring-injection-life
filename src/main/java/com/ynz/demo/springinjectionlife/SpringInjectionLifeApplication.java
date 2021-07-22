package com.ynz.demo.springinjectionlife;

import com.ynz.demo.springinjectionlife.domain.Authority;
import com.ynz.demo.springinjectionlife.domain.SecurityUser;
import com.ynz.demo.springinjectionlife.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@RestController
@Slf4j
@RequiredArgsConstructor
public class SpringInjectionLifeApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringInjectionLifeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SecurityUser user = new SecurityUser();
        user.setUserName("foo");
        user.setPassword(passwordEncoder.encode("foo"));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        Authority admin = new Authority();
        admin.setAuthority("ADMIN");
        user.addAuthority(admin);

        Authority userAuth = new Authority();
        userAuth.setAuthority("USER");
        user.addAuthority(userAuth);

        userRepository.save(user);
    }

}
