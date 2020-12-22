package controller;

import com.example.test.studentportal.controller.StudentController;
import com.example.test.studentportal.model.Student;
import com.example.test.studentportal.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock StudentService studentService;

    private StudentController studentController;

    @BeforeEach
    public void init() {
        studentController = new StudentController(studentService);
    }

    @Test
    public void validStudentListIsObtained(){

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student());

        //mocking scenario
        when(studentService.getStudentList()).thenReturn(studentList);
        ConcurrentModel model = new ConcurrentModel();

        //testing scenario
        ModelAndView result = studentController.studentList(model, "test");

        // testing with assertion
        assertEquals("/student/list", result.getViewName());
        assertEquals("test", model.getAttribute("message"));
        assertNotNull(model.getAttribute("studentList"));
    }

    @Test
    public void newStudent(){
        ModelAndView result = studentController.newStudent();
        assertEquals("/student/new", result.getViewName());
    }

    @ParameterizedTest
    @CsvSource({",", ",TEST", "Java,"})
    public void createInvalidDataStudent(String firstName, String email){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);

        //testing scenario
        ModelAndView result = studentController.createStudent(student);

        // testing with assertion
        assertEquals("/student/new", result.getViewName());
        assertEquals("inValidData", result.getModel().get("errorCode"));
    }

    @ParameterizedTest
    @CsvSource({"Java,tr@gmail.com, ppt, jbp, 12", "Java,tr@gmail.com, ppt, , 12", "Java,tr@gmail.com, ppt, jbp, "})
    @ValueSource
    public void createValidStudent(String firstName, String email, String lastName, String batch, Long id){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);
        student.setLastName(lastName);
        student.setBatch(batch);
        student.setId(id);

        //mocking scenario
        when(studentService.addStudent(student)).thenReturn(student);

        //testing scenario
        ModelAndView result = studentController.createStudent(student);
        assertEquals("redirect:/student/list?message=created", result.getViewName());

    }
    @ParameterizedTest
    @CsvSource({"java,test"})
    public void createInvalidStudent(String firstName, String email){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);

        //mocking scenario
        when(studentService.addStudent(student)).thenReturn(null);

        //testing scenario
        ModelAndView result = studentController.createStudent(student);

        // testing with assertion
        assertEquals("/student/new", result.getViewName());
        assertEquals("systemError", result.getModel().get("errorCode"));

    }

    @ParameterizedTest
    @CsvSource({"java,test,17"})
    public void editStudent(String firstName, String email, Long id){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);
        student.setId(id);

        when(studentService.getStudentWithId(id)).thenReturn(java.util.Optional.of(student));
        ConcurrentModel model = new ConcurrentModel();

        //testing scenario
        ModelAndView result = studentController.editStudent(model, id);

        // testing with assertion
        assertEquals("/student/edit", result.getViewName());
    }


    @ParameterizedTest
    @CsvSource({",", ",TEST", "Java,"})
    public void updateInvalidDataStudent(String firstName, String email) throws Exception {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);

        ConcurrentModel model = new ConcurrentModel();

        ModelAndView result = studentController.updateForm(model, student);

        // testing with assertion
        assertEquals("/student/edit", result.getViewName());
        assertEquals("inValidData", model.get("errorCode"));
    }


    @ParameterizedTest
    @CsvSource({"java,test"})
    public void updateValidStudent(String firstName, String email){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);
        ConcurrentModel model = new ConcurrentModel();
        //mocking scenario
        when(studentService.updateStudent(student)).thenReturn(true);

        //testing scenario
        ModelAndView result = studentController.updateForm(model, student);
        assertEquals("redirect:/student/list?message=updated", result.getViewName());

    }
    @ParameterizedTest
    @CsvSource({"java,test"})
    public void updateInvalidStudent(String firstName, String email){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);

        ConcurrentModel model = new ConcurrentModel();

        //mocking scenario
        when(studentService.updateStudent(student)).thenReturn(false);

        //testing scenario
        ModelAndView result = studentController.updateForm(model, student);

        // testing with assertion
        assertEquals("redirect:/error", result.getViewName());

    }

    @ParameterizedTest
    @CsvSource({"java,test,17"})
    public void deleteStudent(String firstName, String email, Long id){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);
        student.setId(id);

        when(studentService.deleteStudentWithId(id)).thenReturn(true);

        ModelAndView result = studentController.deleteStudent(id);

        assertEquals("redirect:/student/list?message=deleted", result.getViewName());
    }

    @ParameterizedTest
    @CsvSource({"java,test,17"})
    public void deleteFalseStudent(String firstName, String email, Long id){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);
        student.setId(id);

        when(studentService.deleteStudentWithId(id)).thenReturn(false);

        ModelAndView result = studentController.deleteStudent(id);

        assertEquals("redirect:/error", result.getViewName());
    }


}
