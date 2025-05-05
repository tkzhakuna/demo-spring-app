package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;


@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public void saveStudent(Student student){
        int age=0;
        if(student != null){
             age=Period.between(student.getDob(), LocalDate.now()).getYears();
            if(age<18){
                log.info("Student is too young to register");

            }else{
                studentRepository.save(student);
                log.info("Student registered successfully");
            }
        }
    }
}
