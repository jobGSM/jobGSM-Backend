package com.example.jobgsm.domain.application.presentation.dto.response;

import com.example.jobgsm.domain.application.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantsResponse {

    
    private String name;
    private String grade;

    public ApplicantsResponse(Application application) {
        this.name = application.getName();
        this.grade = application.getGrade();
    }
}
