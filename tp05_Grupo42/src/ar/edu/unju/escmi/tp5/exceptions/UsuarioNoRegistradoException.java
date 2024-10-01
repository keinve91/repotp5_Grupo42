package ar.edu.unju.escmi.tp5.exceptions;

public class UsuarioNoRegistradoException extends Exception {
    private static final long serialVersionUID = 1L; // Identificador único de versión

    public UsuarioNoRegistradoException(String message) {
        super(message);
    }
}
