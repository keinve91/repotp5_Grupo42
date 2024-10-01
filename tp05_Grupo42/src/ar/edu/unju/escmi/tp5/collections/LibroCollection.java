package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.dominio.Libro;
import ar.edu.unju.escmi.tp5.exceptions.LibroNoEncontradoException;

public class LibroCollection {
	public static List<Libro> libros = new ArrayList<Libro>();
	
	public static void agregarLibro(Libro libro) {
		libros.add(libro);
	}
	public static void precargarLibros() {
		if(libros.isEmpty()) {
			libros.add(new Libro(1,"Algebra I", "Armando Rojo", "AB234CD23", true));
			libros.add(new Libro(2,"Algebra II", "Armando Rojo", "AB345EF34", true));
			libros.add(new Libro(3,"Programacion Visual", "Ariel Vega", "CD456GH45", true));
			libros.add(new Libro(4,"Laboratorio en Sistemas Operativos", "Leandro Paredes", "DE567IJ56", true));
			libros.add(new Libro(5,"Ingles VI", "Carolina Veliz", "EF678KL67", true));
			libros.add(new Libro(6,"Base de Datos", "Liberatori", "FG789MN78", true));
		}
	}
	public static Libro buscarLibro (int id) throws LibroNoEncontradoException {
		for(Libro lib : libros) {
			if(lib.getId() == id) {
				return lib;
			}
		}
		throw new LibroNoEncontradoException("El libro con codigo " + id + " no fue encontrado..");
	}
}
