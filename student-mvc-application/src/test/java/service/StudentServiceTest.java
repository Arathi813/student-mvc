package service;

import com.example.test.studentportal.domain.DomainStudent;
import com.example.test.studentportal.model.Student;
import com.example.test.studentportal.repository.StudentRepository;
import com.example.test.studentportal.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    private static final Long IDNOTINDB = (long)32;
    private final static Long ID_1 = (long)1;
    private final static String STUDENT_1 = "student1";
    private final static String LNAME_1 = "std";
    private final static String EMAIL_1 = "student1@gmail.com";
    private final static String BATCH_1 = "ecs";

    private final static Long ID_2 = Long.valueOf(2);
    private final static String STUDENT_2 = "student2";
    private final static String LNAME_2 = "piu";
    private final static String EMAIL2 = "student2@gmail.com";
    private final static String BATCH_2 = "ttp";

    @Mock
    StudentRepository studentRepository;

    private StudentService studentService;
    private List<Student> students;
    private Optional<Student> serviceStudent;
    private Student student;
    private  Student validStudent;
    private  DomainStudent validDomainStudent;
    private DomainStudent domainStudent;
    private Boolean deleteResponse;


    @BeforeEach
    public void init() {
        studentService = new StudentService(studentRepository);
    }

    //shouldReturnValidListIfRepositoryReturnsValidStudents
    @Test
    public void shouldReturnValidListIfRepositoryReturnsValidStudents() {
        mockServiceMethodstudentListWithValidValues();
        callGetStudentListMethod();
        verifyGetStudentListSucessResponse();

    }
    @Test
    public void shouldReturnEmptyLIstIfRepositoryReturnsEmpty() {
        mockServiceMethodstudentListWithEmptyValue();
        callGetStudentListMethod();
        verifyStudentListIsEmpty();
    }

    @Test
    public void shouldReturnValidStudentIfRepositoryReturnsValidStudent(){
        mockServiceMethodGetValidStudent();
        callGetStudentWithIdServiceMethod(ID_1);
        verifyStudentIsValid();
    }

    @Test
    public void shouldReturnEmptyStudentIfRepositoryReturnsEmptyStudent(){
        mockServiceMethodGetNoValidStudent();
        callGetStudentWithIdServiceMethod(IDNOTINDB);
        verifyNoStudent();
    }

    private void verifyGetStudentListSucessResponse() {
        assertNotNull(students);
        assertEquals(2, students.size());
        assertEquals(STUDENT_1, students.get(0).getFirstName());
        assertEquals(STUDENT_2, students.get(1).getFirstName());
        assertEquals(EMAIL_1, students.get(0).getEmail());
        assertEquals(EMAIL2, students.get(1).getEmail());
    }

    private void callGetStudentListMethod() {
        students = studentService.getStudentList();
    }

    private List<DomainStudent> getDomainStudentList() {
        List<DomainStudent> domainStudents = new ArrayList<>();
        domainStudents.add(0, new DomainStudent(ID_1 ,STUDENT_1, LNAME_1, EMAIL_1));
        domainStudents.add(1, new DomainStudent(ID_2, STUDENT_2, LNAME_2, EMAIL2));
        return domainStudents;
    }

    private void mockServiceMethodstudentListWithValidValues() {
        when(studentRepository.findAll()).thenReturn(getDomainStudentList());
    }

    private void mockServiceMethodstudentListWithEmptyValue() {
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());
    }

    private void verifyStudentListIsEmpty() {
        assertEquals(0, students.size());
    }

    private void verifyStudentIsValid() {
        student = serviceStudent.get();
        assertNotNull(studentService);
        assertEquals(STUDENT_1, student.getFirstName());
        assertEquals(EMAIL_1, student.getEmail());
        assertEquals(ID_1, student.getId());
    }

    private void callGetStudentWithIdServiceMethod(Long id) {

        serviceStudent = studentService.getStudentWithId(id);
    }

    private void mockServiceMethodGetValidStudent() {
        Student student = new Student(STUDENT_1, LNAME_1, EMAIL_1, ID_1, BATCH_1);
        when(studentRepository.getOne(student.getId())).thenReturn(new DomainStudent(ID_1, STUDENT_1, LNAME_1, EMAIL_1));
    }
    private void verifyNoStudent() {
        assertFalse(serviceStudent.isPresent());
    }

    private void mockServiceMethodGetNoValidStudent() {
        when(studentRepository.getOne(IDNOTINDB)).thenReturn(null);
    }


}
