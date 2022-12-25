package com.example.jobgsm.domain3.signup.repository;


import com.example.jobgsm.domain3.signup.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList,String> {
}