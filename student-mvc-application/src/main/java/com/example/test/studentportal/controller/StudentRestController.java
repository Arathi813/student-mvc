package com.example.test.studentportal.controller;

import com.example.test.studentportal.model.ErrorResponse;
import com.example.test.studentportal.model.Student;
import com.example.test.studentportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StudentRestController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getStudentList();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> studentEdit( @PathVariable("id") Long id) {
        Optional<Student> optionalStudent = studentService.getStudentWithId(id);
        if(optionalStudent.isPresent()){
            return new ResponseEntity(optionalStudent.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/student")
    public Student createUser(@Valid @RequestBody Student student) {
        return studentService.addStudent(student);
    }


    @PutMapping("/student/{id}")
    public Boolean studentUpdate(@PathVariable("id") Long id, @Valid @RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/student/{id}")
    public Boolean deleteUser(@PathVariable("id") Long id){
        return studentService.deleteStudentWithId(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return new ResponseEntity(new ErrorResponse("NO_VALID_REQUEST", exception.getBindingResult().getFieldErrors().stream().map(err -> err.getDefaultMessage())
                .collect(Collectors.joining(", "))), HttpStatus.BAD_REQUEST);
    }

}
