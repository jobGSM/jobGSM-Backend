package com.example.jobgsm.domain.email.domain.repository;

import com.example.jobgsm.domain.email.domain.entity.EmailAuth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface EmailAuthRepository extends CrudRepository<EmailAuth, String> {
}

