package com.oskarro.codecity.service;

import com.oskarro.codecity.entities.Coder;
import com.oskarro.codecity.repositories.CoderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoderService {

    private CoderRepository coderRepository;

    public CoderService(CoderRepository coderRepository) {
        this.coderRepository = coderRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void save(Coder coder) {
        coder.setPassword(passwordEncoder().encode(coder.getPassword()));
    }

    public Coder getCoder(String username) {
        return coderRepository.findByUsername(username);
    }

    public List<Coder> getAllCoder() {
        return coderRepository.findAll();
    }
}
