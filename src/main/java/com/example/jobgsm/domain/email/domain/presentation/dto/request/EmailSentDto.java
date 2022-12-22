package com.example.jobgsm.domain.email.domain.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class EmailSentDto {
    @Email
    private final String email;

    @JsonCreator
    public EmailSentDto(@JsonProperty("email") String email) {
        this.email = email;
    }
}