package org.duo;

import org.duo.domain.Student;
import org.duo.repository.NotaXMLRepo;
import org.duo.repository.StudentXMLRepo;
import org.duo.repository.TemaXMLRepo;
import org.duo.service.Service;
import org.duo.validation.NotaValidator;
import org.duo.validation.StudentValidator;
import org.duo.validation.TemaValidator;
import org.duo.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.duo.Util.makeTempFile;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAddStudent {
    private StudentXMLRepo studentXMLRepository;
    private NotaXMLRepo notaXMLRepository;
    private TemaXMLRepo temaXMLRepository;

    private Service service;

    @BeforeEach
    void setUp() throws IOException {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(makeTempFile("fisiere/Studenti.xml", "xml"));
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(makeTempFile("fisiere/Teme.xml", "xml"));
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(makeTempFile("fisiere/Note.xml", "xml"));

        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void testAddStudentSuccess() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void testAddStudentFailure() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC1_BBT_EC() {
        Student student = new Student("0", "Albert", 935, "mail");
        assertEquals(service.addStudent(student), null);
    }

    @Test
    public void TC2_BBT_EC() {
        Student student = new Student("1", "Albert", 109, "mail");

        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            assertEquals(e.getMessage(), "Grupa incorecta!");
        }
    }

    @Test
    public void TC3_BBT_EC() {
        Student student = new Student("2", "Diana", 937, "mail");
        assertEquals(service.addStudent(student), null);
    }


    @Test
    public void TC4_BBT_EC() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC5_BBT_EC() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC6_BBT_EC() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC7_BBT_EC() {
        Student student = new Student("1", "", 935, "mail");
        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            assertEquals(e.getMessage(), "Nume incorect!");
        }
    }

    @Test
    public void TC8_BBT_EC() {
        Student student = new Student("1", null, 935, "mail");
        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            assertEquals(e.getMessage(), "Nume incorect!");
        }
    }

    @Test
    public void TC9_BBT_EC() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC10_BBT_EC() {
        Student student = new Student("1", "Diana", 935, "");
        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            assertEquals(e.getMessage(), "Email incorect!");
        }
        ;
    }

    @Test
    public void TC11_BBT_EC() {
        Student student = new Student("1", "Diana", 935, null);
        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            assertEquals(e.getMessage(), "Email incorect!");
        }
        ;
    }

    @Test
    public void TC12_BBT_EC() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC13_BBT_EC() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
        System.out.println("bongo");
    }
}