package com.tuition.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestStudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
}