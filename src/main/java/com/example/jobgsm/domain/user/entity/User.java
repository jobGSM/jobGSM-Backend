package com.example.jobgsm.domain.user.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userPassword;
    private String userName;
    private Integer userGrade;

    public void updateUserId(String userId) {
        this.userId = userId;
    }
    public void updateUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public void updateUserName(String userName) {
        this.userName = userName;
    }
    public void updateUserGrade(Integer userGrade) {
        this.userGrade = userGrade;
    }
}
