package ar.edu.unju.escmi.tp5.dominio;
import java.time.LocalDate;

public class Prestamo {
	public int id; 
	public LocalDate fechaPrestamo;
	public LocalDate fechaDevolucion; 
	public Libro libro;
	public Usuario usuario; 
	
	public void registrarDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
		libro.estado = true;
		
	}
	
	public void mostrarDatos() {
        System.out.println("Préstamo ID: " + id + ", Fecha Préstamo: " + fechaPrestamo);
        System.out.println("Fecha Devolución: " + (fechaDevolucion != null ? fechaDevolucion : "No devuelto"));
        libro.mostrarDatos();
        usuario.mostrarDatos();
    }
}
