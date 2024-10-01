package ar.edu.unju.escmi.tp5.main;

import ar.edu.unju.escmi.tp5.collections.LibroCollection;
import ar.edu.unju.escmi.tp5.dominio.Libro;
import ar.edu.unju.escmi.tp5.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.tp5.exceptions.LibroNoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        LibroCollection.precargarLibros();

        do {
            System.out.println("=== Sistema de Gestión de Biblioteca ===");
            System.out.println("1 - Registrar libro");
            System.out.println("2 - Registrar usuario");
            System.out.println("3 - Realizar préstamo de libro");
            System.out.println("4 - Registrar devolución de libro");
            System.out.println("5 - Listar libros disponibles");
            System.out.println("6 - Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea en blanco

            switch (opcion) {
                case 1:
                    registrarLibro(scanner);
                    break;

                case 2:
                    registrarLibro(scanner);
                    break;

                case 3:
                    registrarLibro(scanner);
                    break;

                case 4:
                    registrarLibro(scanner);
                    break;

                case 5:
                    listarLibros();
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }

        } while (opcion != 6);

        scanner.close();
    }

    // Método para registrar un libro
    public static void registrarLibro(Scanner scanner) {
        System.out.println("=== Registrar Libro ===");
        Libro nuevoLibro = new Libro();

        System.out.print("Ingrese ID del libro: ");
        nuevoLibro.id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese título del libro: ");
        nuevoLibro.titulo = scanner.nextLine();

        System.out.print("Ingrese autor del libro: ");
        nuevoLibro.autor = scanner.nextLine();

        System.out.print("Ingrese ISBN del libro: ");
        nuevoLibro.isbn = scanner.nextLine();

        nuevoLibro.estado = true;

        LibroCollection.agregarLibro(nuevoLibro);
        System.out.println("Libro registrado exitosamente.");
    }
 
    public static void listarLibros() {
        System.out.println("=== Listar Libros Disponibles ===");
        for (Libro libro : LibroCollection.libros) {
            if (libro.estado) {
                libro.mostrarDatos();
                System.out.println("*****										*****");

            }
        }
    }

   
}
