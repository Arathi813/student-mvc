package com.example.test.studentportal.service;

import com.example.test.studentportal.domain.DomainStudent;
import com.example.test.studentportal.exception.StudentNotFoundException;

import com.example.test.studentportal.model.Student;
import com.example.test.studentportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentService {

    final private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    List<Student> studentList = new ArrayList<Student>();

    public List<Student> getStudentList() {
        List<Student> modelStudents = new ArrayList<>();
        List<DomainStudent> domainStudents = studentRepository.findAll();
        for (DomainStudent domainStudent : domainStudents) {
            modelStudents.add(getStudentFromDomainStudent(domainStudent));
        }
        return modelStudents;
    }

    public Optional<Student> getStudentWithId(Long id) {
        DomainStudent domainStudent = studentRepository.getOne(id);
        if(domainStudent != null) {
            return Optional.of(getStudentFromDomainStudent(domainStudent));
        }
        return Optional.empty();
    }

    public Student addStudent(Student student) {
        DomainStudent domainStudent = new DomainStudent(student.getId() ,student.getFirstName(), student.getLastName(), student.getEmail());
        return getStudentFromDomainStudent(studentRepository.saveAndFlush(domainStudent));
    }

    public Boolean deleteStudentWithId(Long id) {
        DomainStudent domainStudent = studentRepository.getOne(id);
        if (domainStudent == null) {
            throw new StudentNotFoundException("Entity Not Found");
        }
        studentRepository.delete(domainStudent);
        return true;
    }

    public Boolean updateStudent(Student student) {
        try{
            DomainStudent domainStudent = studentRepository.getOne(student.getId());
            domainStudent.setFirstName(student.getFirstName());
            domainStudent.setEmail(student.getEmail());
            domainStudent.setLastName(student.getLastName());
            studentRepository.save(domainStudent);
            return true;
        }
        catch (EntityNotFoundException e){
            return false;
        }
    }

    private Student getStudentFromDomainStudent(DomainStudent domainStudent){
        Student studentObject = new Student();
        studentObject.setFirstName(domainStudent.getFirstName());
        studentObject.setEmail(domainStudent.getEmail());
        studentObject.setId(domainStudent.getId());
        return studentObject;
    }
}
