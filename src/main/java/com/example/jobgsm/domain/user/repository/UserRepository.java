package com.example.jobgsm.domain.user.repository;

import com.example.jobgsm.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean existsByEmail(String email);

}
