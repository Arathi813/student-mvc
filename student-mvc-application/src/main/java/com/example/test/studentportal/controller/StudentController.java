package com.example.test.studentportal.controller;

import com.example.test.studentportal.model.Student;
import com.example.test.studentportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.*;

@Controller
public class StudentController {

    final private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public ModelAndView studentList(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message);
        model.addAttribute("studentList", studentService.getStudentList());
        return  new ModelAndView("/student/list");
    }

    @RequestMapping(value = "/student/new", method = RequestMethod.GET)
    public ModelAndView newStudent(){
        return  new ModelAndView("/student/new");
    }

    @RequestMapping(value = "/student/create",method = RequestMethod.POST)
    public ModelAndView createStudent(@ModelAttribute Student student){
        Map<String, Object> model = new HashMap<>();
        if(StringUtils.isEmpty(student.getFirstName()) || StringUtils.isEmpty(student.getEmail())){
            model.put("errorCode", "inValidData");
        } else if(studentService.addStudent(student)!=null) {
            return new ModelAndView("redirect:/student/list?message=created");
        } else {
            model.put("errorCode", "systemError");
        }
        return new ModelAndView("/student/new", model);
    }

    @RequestMapping(value = "student/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editStudent(Model model, @PathVariable("id") Long id) {
        Optional<Student> optionalStudent = studentService.getStudentWithId(id);
        if(optionalStudent.isPresent()) {
            model.addAttribute("student", optionalStudent.get());
            return new ModelAndView("/student/edit");
        }
        return new ModelAndView("redirect:/error");
    }



    //    @RequestMapping(value="/student/update", method = RequestMethod.PUT)
    @PostMapping(value = "/student/update")
    public ModelAndView updateForm(Model model, @ModelAttribute Student student){

        if(StringUtils.isEmpty(student.getFirstName()) || StringUtils.isEmpty(student.getEmail())){
            model.addAttribute("errorCode", "inValidData");
            return new ModelAndView("/student/edit");
        } else {
            if(studentService.updateStudent(student)) {
                return new ModelAndView("redirect:/student/list?message=updated");
            }
            return new ModelAndView("redirect:/error");
        }
    }

    @GetMapping(value = "/student/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable("id")Long id){
        if(studentService.deleteStudentWithId(id)) {
            return new ModelAndView("redirect:/student/list?message=deleted");
        }
        return new ModelAndView("redirect:/error");
    }

}
