package com.example.jobgsm.domain.auth.repository;


import com.example.jobgsm.domain.auth.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList,String> {
}