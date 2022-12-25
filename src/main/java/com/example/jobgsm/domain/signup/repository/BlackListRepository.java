package com.example.jobgsm.domain.signup.repository;


import com.example.jobgsm.domain.signup.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList,String> {
}