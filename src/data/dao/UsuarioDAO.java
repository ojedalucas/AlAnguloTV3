package data.dao;

import java.sql.*;
import java.util.ArrayList;

import model.domain.Usuario;

public interface UsuarioDAO {
	public void cargarUsuario(Usuario u) throws SQLException;
	public Usuario buscarPorNombreUsuario(String u) throws SQLException;

	public boolean verificarNomUsuario(String u) throws SQLException;
	public boolean verificarMail(String m)throws SQLException;
	
	public ArrayList<Usuario> listarUsuarios() throws SQLException;
	public Usuario buscarPorID(int ID) throws SQLException;
	
	public Usuario iniciarSesion(String email, String contrasenia) throws SQLException;
}
