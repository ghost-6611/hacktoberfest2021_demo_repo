package com.globalexceptionhandling.example.service;

import com.globalexceptionhandling.example.model.Student;
import com.globalexceptionhandling.example.model.StudentInput;
import com.globalexceptionhandling.example.repo.IStudentRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class StudentService {
    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student insertStudent(StudentInput studentInput) {
        Student student = Student.builder().active(true).firstName(studentInput.getFirstName()).lastName(studentInput.getLastName()).build();
        return studentRepository.save(student);
    }

    public Student getStudentDetails(long id) {
        return studentRepository.findById(id).get();
    }

    public int deleteStudent(long id) {
        int a = 0;
            a = studentRepository.deleteStudent(id);
        return a;
    }

    public Student updateStudent(StudentInput studentInput,Student student)
    {
            student.setFirstName(studentInput.getFirstName());
            student.setLastName(studentInput.getLastName());
            return studentRepository.save(student);
    }
}
