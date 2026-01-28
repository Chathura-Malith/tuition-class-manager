package com.tuition.backend.service;

import com.tuition.backend.dto.request.RequestStudentDto;
import com.tuition.backend.dto.response.ResponseStudentDto;
import com.tuition.backend.dto.response.paginated.StudentPaginatedDto;

public interface StudentService {

    ResponseStudentDto createStudent(RequestStudentDto requestStudentDto);

    ResponseStudentDto getStudentById(long id);

    ResponseStudentDto updateStudent(long id, RequestStudentDto requestStudentDto);

    void deleteStudent(long id);

    StudentPaginatedDto getAllStudents(int page, int size, String searchText);
}