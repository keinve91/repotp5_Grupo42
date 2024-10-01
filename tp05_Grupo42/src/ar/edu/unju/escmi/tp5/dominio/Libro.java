package ar.edu.unju.escmi.tp5.dominio;

public class Libro {
    public int id;
    public String titulo;
    public String autor;
    public String isbn;
    public boolean estado;
	public Libro(int id, String titulo, String autor, String isbn, boolean estado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.estado = estado;
	}
    public Libro() {
		// TODO Auto-generated constructor stub
	}
    
	public void mostrarDatos() {
        System.out.println("ID: " + id + ", Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn);
        System.out.println("Estado: " + (estado ? "Disponible" : "No Disponible"));
    }
    public boolean estaDisponible() {
        return estado;
    }
	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setDisponible(boolean estado) {
        this.estado = estado;
    }


	
}
