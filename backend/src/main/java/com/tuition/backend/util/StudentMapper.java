package com.tuition.backend.util;

import com.tuition.backend.dto.request.RequestStudentDto;
import com.tuition.backend.dto.response.ResponseEnrollmentDetailsDto;
import com.tuition.backend.dto.response.ResponseStudentDto;
import com.tuition.backend.entity.Enrollment;
import com.tuition.backend.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    ResponseStudentDto toResponseStudentDto(Student student);

    Student toStudent(RequestStudentDto requestStudentDto);

    List<ResponseStudentDto> toResponseStudentDtoList(List<Student> students);

    @Mapping(source = "course.title", target = "courseName")
    ResponseEnrollmentDetailsDto toResponseEnrollmentDetailsDto(Enrollment enrollment);
}