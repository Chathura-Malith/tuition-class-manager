package com.tuition.backend.api;

import com.tuition.backend.dto.request.RequestStudentDto;
import com.tuition.backend.dto.response.ResponseStudentDto;
import com.tuition.backend.dto.response.paginated.StudentPaginatedDto;
import com.tuition.backend.service.StudentService;
import com.tuition.backend.util.StandardResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getStudentById(
            @PathVariable @Min(value = 1, message = "ID must be greater than 0") long id) {

        ResponseStudentDto student = studentService.getStudentById(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "Student Fetched Successfully!", student),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateStudent(
            @PathVariable @Min(value = 1, message = "ID must be greater than 0") long id,
            @Valid @RequestBody RequestStudentDto requestStudentDto) {

        ResponseStudentDto updatedStudent = studentService.updateStudent(id, requestStudentDto);

        return new ResponseEntity<>(
                new StandardResponse(200, "Student Updated Successfully!", updatedStudent),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteStudent(
            @PathVariable @Min(value = 1, message = "ID must be greater than 0") long id) {

        studentService.deleteStudent(id);

        return new ResponseEntity<>(
                new StandardResponse(204, "Student Deleted Successfully!", null),
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"searchText", "page", "size"})
    public ResponseEntity<StandardResponse> getAllStudents(
            @RequestParam(value = "searchText", defaultValue = "") String searchText,
            @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "size", defaultValue = "10") @Min(1) int size
    ) {
        StudentPaginatedDto pagedData = studentService.getAllStudents( page, size, searchText);

        return new ResponseEntity<>(
                new StandardResponse(200, "Student List Fetched Successfully!", pagedData),
                HttpStatus.OK
        );
    }
}
