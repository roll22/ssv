package org.duo;

import org.duo.domain.Nota;
import org.duo.domain.Student;
import org.duo.domain.Tema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class Util {

    public static Student makeValidStudent() {
        return new Student("uniqueId", "nume", 1, "mail@mail.mail");
    }

    public static Tema makeValidAssignment() {
        return new Tema("uniqueId", "desc", 1, 1);
    }

    public static String makeTempFile(String inputResource, String extension) throws IOException {
        Path tempFile = Files.createTempFile(java.util.UUID.randomUUID().toString(), extension);
        Files.write(tempFile, ClassLoader.getSystemResource(inputResource).openStream().readAllBytes());
        return tempFile.toString();
    }

    public static Nota makeValidGrade() {
        return new Nota("uniqueId", "uniqueId", "uniqueId", 10, LocalDate.of(2018,10,2));
    }
}
