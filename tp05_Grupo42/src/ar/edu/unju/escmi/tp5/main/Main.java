package ar.edu.unju.escmi.tp5.main;

import ar.edu.unju.escmi.tp5.collections.*;
import ar.edu.unju.escmi.tp5.dominio.*;
import ar.edu.unju.escmi.tp5.exceptions.LibroNoEncontradoException;
import ar.edu.unju.escmi.tp5.exceptions.UsuarioNoRegistradoException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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
            System.out.println("====================================================");

            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        registrarLibro(scanner);
                        break;

                    case 2:
                        registrarUsuario(scanner);
                        break;

                    case 3:
                        realizarPrestamo(scanner); 
                        break;

                    case 4:
                        registrarDevolucion(scanner);
                        break;

                    case 5:
                        listarLibros();
                        break;

                    case 6:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion invalida, intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Ingrese un numero!!!!");
                scanner.nextLine(); 
                opcion = -1; 
            }
        } while (opcion != 6);

        scanner.close();
    }
    
    public static void registrarUsuario(Scanner scanner) {
        try {
            System.out.println("Seleccione el tipo de usuario a registrar:");
            System.out.println("1. Alumno");
            System.out.println("2. Bibliotecario");


            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Ingrese ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ingrese Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese Apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Ingrese Email: ");
            String email = scanner.nextLine();
            
            if (opcion == 1) {
                System.out.print("Ingrese Curso: ");
                String curso = scanner.nextLine();

                System.out.print("Ingrese Número de Libreta: ");
                int numeroDeLibreta = scanner.nextInt();

                Alumno alumno = new Alumno(id, nombre, apellido, email, curso, numeroDeLibreta);
                UsuarioCollection.agregarUsuario(alumno);

            }  else if (opcion == 2) {
                System.out.print("Ingrese Legajo: ");
                String legajo = scanner.nextLine();

                Bibliotecario bibliotecario = new Bibliotecario(id, nombre, apellido, email, legajo);
                UsuarioCollection.agregarUsuario(bibliotecario);
            } else {
                System.out.println("Opción no válida.");
            }

            System.out.println("Usuario registrado con éxito.");

        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida. Por favor, ingrese los datos correctos.");
            scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Error: Entrada no recibida correctamente.");
        } catch (IllegalStateException e) {
            System.out.println("Error: Scanner ya ha sido cerrado.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
    
    
    
    
    

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
                System.out.println(" ");
                libro.mostrarDatos();
                System.out.println("*****************************************************************************");

            }
        }
    }
    public static void realizarPrestamo(Scanner scanner) {
        System.out.println("=== Realizar Préstamo ===");
        try {
            System.out.print("Ingrese el ID del préstamo: ");
            int idPrestamo = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Ingrese el código del libro a prestar: ");
            int codigoLibro = scanner.nextInt();
            scanner.nextLine();

            Libro libro = LibroCollection.buscarLibro(codigoLibro);
            System.out.println("Libro encontrado: " + libro.getTitulo());


            System.out.print("Ingrese ID del usuario que realiza el préstamo: ");
            int idUsuario = scanner.nextInt();
            scanner.nextLine();  

            Usuario usuario = UsuarioCollection.buscarUsuarioPorId(idUsuario);
            if (usuario == null) {
                throw new UsuarioNoRegistradoException("Error: Usuario no registrado.");
            }
            System.out.println("Usuario encontrado: " + usuario.getNombre());


            System.out.print("Ingrese la fecha de préstamo (dd/MM/yyyy): ");
            String fechaPrestamoStr = scanner.nextLine();

            PrestamoCollection.realizarPrestamo(idPrestamo, codigoLibro, usuario, fechaPrestamoStr);

        } catch (LibroNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (UsuarioNoRegistradoException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void registrarDevolucion(Scanner scanner) {
        System.out.println("=== Registrar Devolución ===");
        
        System.out.println("=== Lista de Préstamos Activos ===");
        PrestamoCollection.listarPrestamos(); 

        try {

            System.out.print("Ingrese el ID del préstamo: ");
            int idPrestamo = scanner.nextInt();
            scanner.nextLine(); 


            System.out.print("Ingrese la fecha de devolución (dd/MM/yyyy): ");
            String fechaDevolucionStr = scanner.nextLine();

            try {
            } catch (IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());
                return;  
            }

            PrestamoCollection.registrarDevolucion(idPrestamo, fechaDevolucionStr);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



   

}
