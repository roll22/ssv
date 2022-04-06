package org.duo;

import org.duo.repository.NotaXMLRepo;
import org.duo.repository.StudentXMLRepo;
import org.duo.repository.TemaXMLRepo;
import org.duo.service.Service;
import org.duo.validation.NotaValidator;
import org.duo.validation.StudentValidator;
import org.duo.validation.TemaValidator;
import org.duo.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.duo.Util.makeTempFile;
import static org.duo.Util.makeValidAssignment;

public class ServiceWhiteBoxTest {
    private Service service;

    @BeforeEach
    void setup() throws IOException {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(makeTempFile("fisiere/Studenti.xml", "xml"));
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(makeTempFile("fisiere/Teme.xml", "xml"));
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(makeTempFile("fisiere/Note.xml", "xml"));

        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    void addTema_emptyId_ValidationException() {
        var assignment = makeValidAssignment();
        assignment.setID("");
        Assertions.assertThrows(ValidationException.class, () -> service.addTema(assignment));
    }

    @Test
    void addTema_nullId_ValidationException() {
        var assignment = makeValidAssignment();
        assignment.setID(null);
        Assertions.assertThrows(NullPointerException.class, () -> service.addTema(assignment));
    }

    @Test
    void addTema_correctId_Success() {
        var assignment = makeValidAssignment();
        assignment.setID("uniqueId");
        Assertions.assertNull(service.addTema(assignment));
    }

    @Test
    void addTema_emptyDescription_ValidationException() {
        var assignment = makeValidAssignment();
        assignment.setDescriere("");
        Assertions.assertThrows(ValidationException.class, () -> service.addTema(assignment));
    }

    @Test
    void addTema_nullDescription_ValidationException() {
        var assignment = makeValidAssignment();
        assignment.setDescriere(null);
        Assertions.assertThrows(NullPointerException.class, () -> service.addTema(assignment));
    }

    @Test
    void addTema_nonEmptyDescription_Success() {
        var assignment = makeValidAssignment();
        assignment.setDescriere("desc");
        Assertions.assertNull(service.addTema(assignment));
    }

    @Test
    void addTema_deadlineLessThan1_ValidationException() {
        var assignment = makeValidAssignment();
        assignment.setDeadline(0);
        Assertions.assertThrows(ValidationException.class, () -> service.addTema(assignment));
    }

    @Test
    void addTema_deadlineGreaterThan14_ValidationException() {
        var assignment = makeValidAssignment();
        assignment.setDeadline(15);
        Assertions.assertThrows(ValidationException.class, () -> service.addTema(assignment));
    }

    @Test
    void addTema_correctDeadline_Success() {
        var assignment = makeValidAssignment();
        assignment.setDeadline(10);
        Assertions.assertNull(service.addTema(assignment));
    }

    @Test
    void addTema_primireLessThan1_ValidationException() {
        var assignment = makeValidAssignment();
        assignment.setPrimire(0);
        Assertions.assertThrows(ValidationException.class, () -> service.addTema(assignment));
    }

    @Test
    void addTema_primireGreaterThan14_ValidationException() {
        var assignment = makeValidAssignment();
        assignment.setPrimire(15);
        Assertions.assertThrows(ValidationException.class, () -> service.addTema(assignment));
    }

    @Test
    void addTema_correctPrimire_Success() {
        var assignment = makeValidAssignment();
        assignment.setPrimire(10);
        Assertions.assertNull(service.addTema(assignment));
    }
}