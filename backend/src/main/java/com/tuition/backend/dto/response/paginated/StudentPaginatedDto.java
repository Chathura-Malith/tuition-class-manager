package com.tuition.backend.dto.response.paginated;

import com.tuition.backend.dto.response.ResponseStudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentPaginatedDto {
    private long dataCount;
    private List<ResponseStudentDto> list;
}