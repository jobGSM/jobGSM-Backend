package com.example.jobgsm.domain.email.repository;

import com.example.jobgsm.domain.email.entity.EmailAuth;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthRepository extends CrudRepository<EmailAuth, String> {
}

