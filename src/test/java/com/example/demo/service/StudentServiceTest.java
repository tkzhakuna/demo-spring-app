package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private Student buildStudent() {
        return Student.builder()
                .dob(LocalDate.of(2000, 1, 1))
                .studentName("John Doe")
                .email("doe@gmail.com").build();

    }

    @Test
    void saveStudent_ValidStudent_StudentSaved() {
        // Arrange
        Student student = buildStudent();
        // Act
        studentService.saveStudent(student);

        // Assert
        verify(studentRepository, times(1)).save(student);
    }


    @Test
    void saveStudent_StudentTooYoung_StudentNotSaved() {
        // Arrange
        Student student = buildStudent();
        student.setDob(LocalDate.of(2023, 1, 1));
        // Act
        studentService.saveStudent(student);
        // Assert
        verify(studentRepository, never()).save(student);
    }

    @Test
    void saveStudent_NullStudent_StudentNotSaved() {
        // Act
        studentService.saveStudent(null);

        // Assert
        verify(studentRepository, never()).save(new Student());
    }
}