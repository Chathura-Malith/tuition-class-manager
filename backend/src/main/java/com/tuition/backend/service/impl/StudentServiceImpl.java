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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Student> selectedStudent = studentRepository.findById(id);

        if (selectedStudent.isEmpty()) {
            throw new NotFoundException("Student not found for ID: " + id);
        }
        Student student = selectedStudent.get();
        if (!student.getEmail().equalsIgnoreCase(requestStudentDto.getEmail())) {
            if (studentRepository.existsByEmail(requestStudentDto.getEmail())) {
                throw new AlreadyExistsException("This Email is already registered with another student!");
            }
        }
        if (!student.getMobile().equals(requestStudentDto.getMobile())) {
            if (studentRepository.existsByMobile(requestStudentDto.getMobile())) {
                throw new AlreadyExistsException("This Mobile number is already registered with another student!");
            }
        }
        student.setFirstName(requestStudentDto.getFirstName());
        student.setLastName(requestStudentDto.getLastName());
        student.setEmail(requestStudentDto.getEmail());
        student.setMobile(requestStudentDto.getMobile());

        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toResponseStudentDto(updatedStudent);
    }

    @Override
    public void deleteStudent(long id) {
        if (!studentRepository.existsById(id)) {
            throw new NotFoundException("Student not found for ID: " + id);
        }

        studentRepository.deleteById(id);
    }

    @Override
    public StudentPaginatedDto getAllStudents(int page, int size, String searchText) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Student> studentsPage;

        if (searchText != null && !searchText.isEmpty()) {
            studentsPage = studentRepository.searchStudents(searchText, pageRequest);
        } else {
            studentsPage = studentRepository.findAll(pageRequest);
        }
        List<ResponseStudentDto> list = studentMapper.toResponseStudentDtoList(studentsPage.getContent());

        return new StudentPaginatedDto(
                studentsPage.getTotalElements(),
                list
        );
    }
}
