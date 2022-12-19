package com.example.jobgsm.domain.user.repository;

import com.example.jobgsm.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
