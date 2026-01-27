package com.tuition.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false,length = 45)
    private String firstName;
    @Column(name = "last_name", nullable = false,length = 45)
    private String lastName;
    @Column(name = "email",length = 100, unique = true)
    private String email;
    @Column( nullable = false,length = 10)
    private String mobile;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Enrollment> enrollments;
}