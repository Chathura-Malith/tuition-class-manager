package com.tuition.backend.repo;

import com.tuition.backend.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    @Query(value = "SELECT * FROM student WHERE first_name LIKE %?1% OR last_name LIKE %?1% OR email LIKE %?1%",
            nativeQuery = true)
    Page<Student> searchStudents(String searchText, Pageable pageable);
}