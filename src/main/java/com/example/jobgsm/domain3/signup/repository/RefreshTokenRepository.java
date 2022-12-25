package com.example.jobgsm.domain3.signup.repository;

import com.example.jobgsm.domain3.signup.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findRefreshTokenByEmail(String email);
}