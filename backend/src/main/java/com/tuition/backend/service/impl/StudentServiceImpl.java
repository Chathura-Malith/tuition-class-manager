package com.tuition.backend.service.impl;

import com.tuition.backend.dto.request.RequestStudentDto;
import com.tuition.backend.dto.response.ResponseStudentDto;
import com.tuition.backend.dto.response.paginated.StudentPaginatedDto;
import com.tuition.backend.entity.Student;
import com.tuition.backend.exception.AlreadyExistsException;
import com.tuition.backend.exception.NotFoundException;
import com.tuition.backend.repo.StudentRepository;
import com.tuition.backend.service.StudentService;
import com.tuition.backend.util.StudentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    @Override
    public ResponseStudentDto createStudent(RequestStudentDto requestStudentDto) {
        if (studentRepository.existsByEmail(requestStudentDto.getEmail())) {
            throw new AlreadyExistsException("This Email is already registered!");
        }

        if (studentRepository.existsByMobile(requestStudentDto.getMobile())) {
            throw new AlreadyExistsException("This Mobile number is already registered!");
        }
        Student student = studentMapper.toStudent(requestStudentDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponseStudentDto(savedStudent);
    }

    @Override
    public ResponseStudentDto getStudentById(long id) {
        Optional<Student> selectedStudent = studentRepository.findById(id);
        if (selectedStudent.isPresent()) {
            return studentMapper.toResponseStudentDto(selectedStudent.get());
        }
        throw new NotFoundException("Student not found for ID: " + id);
    }

    @Override
    public ResponseStudentDto updateStudent(long id, RequestStudentDto requestStudentDto) {
        return null;
    }

    @Override
    public void deleteStudent(long id) {

    }

    @Override
    public StudentPaginatedDto getAllStudents(int page, int size, String searchText) {
        return null;
    }
}
