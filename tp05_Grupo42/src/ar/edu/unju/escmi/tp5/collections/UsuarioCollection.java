package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.dominio.Usuario;

public class UsuarioCollection {
	public static List<Usuario> usuarios = new ArrayList<>();
	
	public static void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
	
	public static List<Usuario> obtenerUsuarios() {
        return usuarios;
    }
	
    public static Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }
    
    public static boolean eliminarUsuarioPorId(int id) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario != null) {
            return usuarios.remove(usuario);
        }
        return false;
    }
	
}
