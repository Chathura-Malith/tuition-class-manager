package com.tuition.backend.api;

import com.tuition.backend.dto.request.RequestStudentDto;
import com.tuition.backend.dto.response.ResponseStudentDto;
import com.tuition.backend.service.StudentService;
import com.tuition.backend.util.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/students")
@CrossOrigin
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StandardResponse> createStudent(@Valid @RequestBody RequestStudentDto requestStudentDto) {
        ResponseStudentDto savedStudent = studentService.createStudent(requestStudentDto);

        return new ResponseEntity<>(
                new StandardResponse(201, "Student Created Successfully!", savedStudent),
                HttpStatus.CREATED
        );
    }
}
