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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TestAddStudent {
    private StudentXMLRepo studentXMLRepository;
    private NotaXMLRepo notaXMLRepository;
    private TemaXMLRepo temaXMLRepository;

    private Service service;

    @Before
    public void setup() {
        StudentValidator vs = new StudentValidator();
        TemaValidator ts = new TemaValidator();

        studentXMLRepository = new StudentXMLRepo("src/test/resources/studenti.xml");
        notaXMLRepository = new NotaXMLRepo("src/test/resources/note.xml");
        temaXMLRepository = new TemaXMLRepo("src/test/resources/teme.xml");
        NotaValidator nv = new NotaValidator(studentXMLRepository, temaXMLRepository);

        service = new Service(studentXMLRepository, vs, temaXMLRepository, ts, notaXMLRepository, nv);

    }

    @After
    public void tearDown() {
        try {
            String defaultFileContent = new String(Files.readAllBytes(Paths.get("src/test/resources/defaultFile.xml")),
                StandardCharsets.UTF_8);

            PrintWriter printWriter = new PrintWriter("src/test/resources/studenti.xml");

            printWriter.print(defaultFileContent);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void TC5_BBT_BVA() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC6_BBT_BVA() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC7_BBT_BVA() {
        Student student = new Student("1", "", 935, "mail");
        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            assertEquals(e.getMessage(), "Nume incorect!");
        }
    }

    @Test
    public void TC8_BBT_BVA() {
        Student student = new Student("1", null, 935, "mail");
        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            assertEquals(e.getMessage(), "Nume incorect!");
        }
    }

    @Test
    public void TC9_BBT_BVA() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC10_BBT_BVA() {
        Student student = new Student("1", "Diana", 935, "");
        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            assertEquals(e.getMessage(), "Email incorect!");
        };
    }

    @Test
    public void TC11_BBT_BVA() {
        Student student = new Student("1", "Diana", 935, null);
        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            assertEquals(e.getMessage(), "Email incorect!");
        };
    }

    @Test
    public void TC12_BBT_BVA() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void TC13_BBT_BVA() {
        Student student = new Student("1", "Diana", 935, "mail");
        assertEquals(service.addStudent(student), student);
    }
}