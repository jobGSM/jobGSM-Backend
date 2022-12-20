package com.example.jobgsm.domain.application.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Application {

    @Id
    private Long id;
    private Long boardId;
    private String userName;
    private Integer userGrade;
}
