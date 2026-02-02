package com.tuition.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEnrollmentDetailsDto {
    private long id;
    private Date enrolledDate;
    private String courseName;
}
