package com.globalexceptionhandling.example.controller;

import com.globalexceptionhandling.example.model.Student;
import com.globalexceptionhandling.example.model.StudentInput;
import com.globalexceptionhandling.example.exception.MandatoryDataException;
import com.globalexceptionhandling.example.response.APIResponse;
import com.globalexceptionhandling.example.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class StudentController {

private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    APIResponse apiResponse = null;

    @PostMapping("/insert")
    public APIResponse insertStudent(@RequestBody  StudentInput studentInput) {
        if(studentInput.getFirstName().isEmpty())
            throw new MandatoryDataException();
       Student student = studentService.insertStudent(studentInput);
        apiResponse = APIResponse.builder().message("Student inserted successfully").status(true).entity(student).build();
        return apiResponse;
    }

    @GetMapping("/getstudent/{id}")
    public APIResponse getStudent(@PathVariable("id") long id) {
         Student student = studentService.getStudentDetails(id);
        apiResponse = APIResponse.builder().message("Student details......").status(true).entity(student).build();
         if(student == null)
             throw new NoSuchElementException();
         return apiResponse;
    }

    @DeleteMapping("/deletestudent/{id}")
    public APIResponse deleteStudent(@PathVariable("id") long id) throws Exception {
        int a = 0;
        if(id > 0) {
            a = studentService.deleteStudent(id);
            if(a > 0)
                apiResponse = APIResponse.builder().message("Student Deleted successfully").status(true).build();
            else
                throw new RuntimeException();
        } else
            throw new NoSuchElementException();
        return apiResponse;
    }
}
