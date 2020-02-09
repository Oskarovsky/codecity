package com.oskarro.codecity;

import com.oskarro.codecity.config.CustomUserDetails;
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

import java.lang.reflect.Array;
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

    /**
     * Password grants are switched on by injecting an AuthenticationManager.
     * Here, we setup the builder so that the userDetailsService is the one we coded.
     * @param builder
     * @param coderRepository
     * @throws Exception
     */
    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, CoderRepository coderRepository,
                                      CoderService coderService) throws Exception {
        if (coderRepository.count() == 0) {
            coderService.save(new Coder("admin", "admin", Arrays.asList(new Role("CODER"), new Role("ACTUATOR") , new Role("ADMIN"))));
        }
        builder.userDetailsService(userDetailsService(coderRepository)).passwordEncoder(passwordEncoder);
    }


    /**
     * We return an istance of our CustomUserDetails.
     * @param coderRepository
     * @return
     */
    private UserDetailsService userDetailsService(final CoderRepository coderRepository) {
        return username -> new CustomUserDetails(coderRepository.findByUsername(username));
    }

}
