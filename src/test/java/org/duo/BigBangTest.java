package org.duo;

import org.duo.domain.Nota;
import org.duo.domain.Student;
import org.duo.domain.Tema;
import org.duo.repository.NotaXMLRepo;
import org.duo.repository.StudentXMLRepo;
import org.duo.repository.TemaXMLRepo;
import org.duo.service.Service;
import org.duo.validation.NotaValidator;
import org.duo.validation.StudentValidator;
import org.duo.validation.TemaValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.duo.Util.makeTempFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigBangTest {
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
    public void testAddTema() {
        Tema tema = new Tema("1", "1", 8, 7);
        assertEquals(service.addTema(tema), tema);
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("1", "Andrei", 231, "mailocu");
        assertEquals(service.addStudent(student), student);
    }

    @Test
    public void testAddNota() {
        double _nota = 3;
        Nota nota = new Nota("1000", "1", "1", _nota, LocalDate.now());

        assertEquals(service.addNota(nota, "jbulangiu"), _nota);
    }

    @Test
    public void testBigBang() {
        Student student = new Student("2", "Alexandru", 231, "mailocu");
        assertEquals(service.addStudent(student), student);
        Tema tema = new Tema("2", "1", 8, 7);
        assertEquals(service.addTema(tema), tema);

        double _nota = 3;
        Nota nota = new Nota("1000", "1000", "9", _nota, LocalDate.now());

        assertEquals(service.addNota(nota, "jbulangiu"), _nota);

    }
}