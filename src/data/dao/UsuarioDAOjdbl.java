package Data.Dao;

import java.sql.*;
import java.util.ArrayList;

import Data.ConnectionManager;
import Model.Domain.DatosPersonales;
import Model.Domain.Usuario;

public class UsuarioDAOjdbl implements UsuarioDAO{
	private Connection connection;

	public UsuarioDAOjdbl() {
		connection= ConnectionManager.getConnection();
	}
	
	@Override
	public void cargarUsuario(Usuario u) throws SQLException {
			Statement stmt = connection.createStatement();

	        String sql = "INSERT INTO USUARIO (NOMBRE_USUARIO, EMAIL, CONTRASENIA, ID_DATOS_PERSONALES)" +
	                     "VALUES ('" + u.getNombreUsuario() + "', " +
	                             "'" + u.getEmail() + "', " + 
	                             "'" + u.getContrasenia() + "', " + 
	                             u.getDatosPersonales().getId() +  
	                             ");";
	        
	        stmt.executeUpdate(sql);
	        stmt.close();	
			
	}
	
	//retorno los datos completos del usuario, si el nombre de usuario no existe retorna null;
	public Usuario buscarPorNombreUsuario(String user) throws SQLException {
		Statement stmt = connection.createStatement();		    
		ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO INNER JOIN DATOS_PERSONALES ON USUARIO.ID_DATOS_PERSONALES=DATOS_PERSONALES.ID");
		Usuario usuario = null;
		
		while (rs.next()) {
			String nombreUsuario=rs.getString("NOMBRE_USUARIO");
			if(nombreUsuario.equals(user)) {
			  int id= rs.getInt("ID");
		      String contrasenia = rs.getString("CONTRASENIA");
		      String email = rs.getString("EMAIL");
		      int id_datos = rs.getInt("ID_DATOS_PERSONALES");
			  String nombre=rs.getString("NOMBRES");
		      String apellido = rs.getString("APELLIDO");
			  int dni = rs.getInt("DNI");
				
		      DatosPersonales persona=new DatosPersonales(id_datos,nombre, apellido, dni);
			  usuario = new Usuario(nombreUsuario,contrasenia, email, id, persona);
			}
		}
		return usuario;
	}

	
	//retorna true si existe
	public boolean verificarNomUsuario(String u) throws SQLException{
		String sql= "SELECT COUNT(*) FROM USUARIO WHERE NOMBRE_USUARIO=?;";
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql);
    	boolean existe=false;  
  		pstmt.setString(1,u);
        ResultSet rs=pstmt.executeQuery();
        int cant=rs.getInt(1);
        rs.close();
        if(cant>0) existe=true;
        pstmt.close();
		return existe;
	}
	//retorna true si existe
	public boolean verificarMail(String m) throws SQLException{
		String sql= "SELECT COUNT(*) FROM USUARIO WHERE EMAIL=?;";
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql);
    	boolean existe=false;  
  		pstmt.setString(1,m);
        ResultSet rs=pstmt.executeQuery();
        int cant=rs.getInt(1);
        rs.close();
        if(cant>0) existe=true;
        pstmt.close();
		return existe;
	}
	
	//retorna una lista de los usuarios
	public ArrayList<Usuario> listarUsuarios() throws SQLException {
		Statement stmt = connection.createStatement();
		ArrayList<Usuario> listaUsuarios= new ArrayList<Usuario>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO INNER JOIN DATOS_PERSONALES ON USUARIO.ID_DATOS_PERSONALES=DATOS_PERSONALES.ID");    
		while (rs.next()) {
			int id= rs.getInt("ID");
			String nombreUsuario=rs.getString("NOMBRE_USUARIO");
			String contrasenia = rs.getString("CONTRASENIA");
		    String email = rs.getString("EMAIL");
		    int id_datos = rs.getInt("ID_DATOS_PERSONALES");
		    String nombre=rs.getString("NOMBRES");
			String apellido = rs.getString("APELLIDO");
			int dni = rs.getInt("DNI");
			
			DatosPersonales persona=new DatosPersonales(id_datos,nombre, apellido, dni);
		    Usuario datos = new Usuario(nombreUsuario,contrasenia, email, id, persona);
		    
		    listaUsuarios.add(datos);
		}
		return listaUsuarios;
	}

	public Usuario buscarPorID(int ID) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO INNER JOIN DATOS_PERSONALES ON USUARIO.ID_DATOS_PERSONALES=DATOS_PERSONALES.ID");
        Usuario usuario = null;

        while (rs.next()) {
            int id=rs.getInt("ID");
            if(id==ID) {
              String nombreUsuario= rs.getString("NOMBRE_USUARIO");
              String contrasenia = rs.getString("CONTRASENIA");
              String email = rs.getString("EMAIL");
              int id_datos = rs.getInt("ID_DATOS_PERSONALES");
              String nombre=rs.getString("NOMBRES");
              String apellido = rs.getString("APELLIDO");
              int dni = rs.getInt("DNI");

              DatosPersonales persona=new DatosPersonales(id_datos,nombre, apellido, dni);
              usuario = new Usuario(nombreUsuario,contrasenia, email, id, persona);
              
            }
        }
        return usuario;
    }

	public Usuario iniciarSesion(String email, String contrasenia) throws SQLException {
		String sql= "SELECT * FROM USUARIO INNER JOIN DATOS_PERSONALES ON USUARIO.ID_DATOS_PERSONALES=DATOS_PERSONALES.ID WHERE EMAIL=? AND CONTRASENIA=?";
		
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql); 
  		pstmt.setString(1,email);
		pstmt.setString(2,contrasenia);
        ResultSet rs=pstmt.executeQuery();

        if (!rs.next()) {
			rs.close();
			pstmt.close();
			return null;
		} else {
			  int id=rs.getInt("ID");
              String nombreUsuario= rs.getString("NOMBRE_USUARIO");
              int id_datos = rs.getInt("ID_DATOS_PERSONALES");
              String nombre=rs.getString("NOMBRES");
              String apellido = rs.getString("APELLIDO");
              int dni = rs.getInt("DNI");
              DatosPersonales persona=new DatosPersonales(id_datos,nombre, apellido, dni);
              Usuario usuario = new Usuario(nombreUsuario,contrasenia, email, id, persona);
			  rs.close();
			  pstmt.close();
			  return usuario;
        }
    }	
}


