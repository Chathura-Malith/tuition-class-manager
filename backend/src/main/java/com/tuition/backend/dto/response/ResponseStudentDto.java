package com.tuition.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}