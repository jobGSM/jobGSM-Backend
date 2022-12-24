package com.example.jobgsm.domain3.signup.repository;

import com.example.jobgsm.domain3.signup.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(String loginId);

}
