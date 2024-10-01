package ar.edu.unju.escmi.tp5.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaUtil {

    public static LocalDate convertirStringLocalDate(String fechaStr) throws IllegalArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDate.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error: Formato de fecha incorrecto, debe ser dd/MM/yyyy.");
        }
    }
}
