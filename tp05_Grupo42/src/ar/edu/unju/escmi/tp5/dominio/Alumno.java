package ar.edu.unju.escmi.tp5.dominio;

public class Alumno extends Usuario{
	
	private String curso;
	private int numeroDeLibreta;
	
	public Alumno(int id, String nombre, String apellido, String email, String curso, int numeroDeLibreta) {
        super(id, nombre, apellido, email);
        this.curso = curso;
        this.numeroDeLibreta = numeroDeLibreta;
    }
	
	@Override
	public void mostrarDatos() {
		System.out.println("Alumno: " + nombre + " " + apellido + ", Curso: " + curso + ", N° de Libreta: " + numeroDeLibreta);
		
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getNumeroDeLibreta() {
		return numeroDeLibreta;
	}

	public void setNumeroDeLibreta(int numeroDeLibreta) {
		this.numeroDeLibreta = numeroDeLibreta;
	}
	
	
	
}
