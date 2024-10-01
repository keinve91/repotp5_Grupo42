package ar.edu.unju.escmi.tp5.collections;

import ar.edu.unju.escmi.tp5.dominio.Prestamo;
import ar.edu.unju.escmi.tp5.dominio.Libro;
import ar.edu.unju.escmi.tp5.dominio.Usuario;
import ar.edu.unju.escmi.tp5.utils.FechaUtil;
import ar.edu.unju.escmi.tp5.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.tp5.exceptions.LibroNoEncontradoException;
import ar.edu.unju.escmi.tp5.exceptions.UsuarioNoRegistradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoCollection {
    public static List<Prestamo> prestamos = new ArrayList<>();

    public static void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        System.out.println("Préstamo registrado exitosamente.");
    }

    public static Prestamo buscarPrestamoPorId(int id) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.id == id) {
                return prestamo;
            }
        }
        return null; 
    }


    public static void realizarPrestamo(int idPrestamo, int idLibro, Usuario usuario, String fechaPrestamoStr) 
        throws LibroNoDisponibleException, LibroNoEncontradoException, UsuarioNoRegistradoException {
        
        Libro libro = LibroCollection.buscarLibro(idLibro);

        if (libro == null) {
            throw new LibroNoEncontradoException("Error: Libro no encontrado.");
        }

        if (!libro.estado) {
            throw new LibroNoDisponibleException("Error: El libro no está disponible para préstamo.");
        }
    
        LocalDate fechaPrestamo;
        try {
            fechaPrestamo = FechaUtil.convertirStringLocalDate(fechaPrestamoStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error: Formato de fecha incorrecto, debe ser dd/MM/yyyy.");
        }

        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.id = idPrestamo;
        nuevoPrestamo.fechaPrestamo = fechaPrestamo;
        nuevoPrestamo.libro = libro;
        nuevoPrestamo.usuario = usuario;

        libro.estado = false;

        agregarPrestamo(nuevoPrestamo);
    }

    public static void registrarDevolucion(int idPrestamo, String fechaDevolucionStr) throws Exception {
        Prestamo prestamo = buscarPrestamoPorId(idPrestamo);

        if (prestamo == null) {
            throw new Exception("Error: Préstamo no encontrado.");
        }

        if (prestamo.fechaDevolucion != null) {
            throw new Exception("Error: El préstamo ya ha sido devuelto.");
        }

        LocalDate fechaDevolucion;
        try {
            fechaDevolucion = FechaUtil.convertirStringLocalDate(fechaDevolucionStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error: Formato de fecha incorrecto, debe ser dd/MM/yyyy.");
        }

        prestamo.registrarDevolucion(fechaDevolucion);
        System.out.println("Devolución registrada exitosamente. El libro está disponible nuevamente.");
    }

    public static void listarPrestamos() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            for (Prestamo prestamo : prestamos) {
                prestamo.mostrarDatos();
            }
        }
    }
}
