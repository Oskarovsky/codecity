package com.oskarro.codecity;

import com.oskarro.codecity.entities.Coder;
import com.oskarro.codecity.entities.Role;
import com.oskarro.codecity.repositories.CoderRepository;
import com.oskarro.codecity.service.CoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class CodecityApplication {

    private PasswordEncoder passwordEncoder;

    public CodecityApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(CodecityApplication.class, args);
    }

}
