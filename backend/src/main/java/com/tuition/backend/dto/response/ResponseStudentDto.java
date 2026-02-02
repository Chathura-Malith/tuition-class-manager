package com.tuition.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;

    private List<ResponseEnrollmentDetailsDto> enrollments;
}