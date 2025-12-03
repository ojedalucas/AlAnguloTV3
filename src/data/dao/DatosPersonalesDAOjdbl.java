package Data.Dao;

import java.sql.*;
import java.util.*;

import Data.ConnectionManager;
import Model.Domain.DatosPersonales;

public class DatosPersonalesDAOjdbl implements DatosPersonalesDAO{
	private Connection connection;

	public DatosPersonalesDAOjdbl() {
		connection= ConnectionManager.getConnection();
	}

	@Override
	public boolean existeDNI(int dni) throws SQLException {
		String sql= "SELECT COUNT(*) FROM DATOS_PERSONALES WHERE DNI=?;";
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql);
    	boolean existe=false;    
  		pstmt.setInt(1,dni);
        ResultSet rs=pstmt.executeQuery();
        int cant=rs.getInt(1);
        rs.close();
        if(cant>0) existe=true;
        pstmt.close();
		return existe;
	}

	public int obtenerIdPorDNI(int dni) throws SQLException {
		String sql = "SELECT ID FROM DATOS_PERSONALES WHERE DNI = ?;";
		PreparedStatement pstmt = connection.prepareStatement(sql);

		pstmt.setInt(1, dni);
		ResultSet rs = pstmt.executeQuery();

		int id = -1; // valor por defecto si no existe

		if (rs.next()) {
			id = rs.getInt("ID");
		}

		rs.close();
		pstmt.close();

		return id;
	}


	@Override
	public void cargarDatos (DatosPersonales nuevosDatos) throws SQLException {
	  String sql = "INSERT INTO DATOS_PERSONALES (NOMBRES, APELLIDO, DNI) " +
                 "VALUES (?, ?, ?)";

      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, nuevosDatos.getNombre());
      pstmt.setString(2, nuevosDatos.getApellido());
      pstmt.setInt(3, nuevosDatos.getDni());

      pstmt.executeUpdate();
      pstmt.close();
    }

	@Override
	public ArrayList<DatosPersonales> listar() throws SQLException {
		Statement stmt = connection.createStatement();
		ArrayList<DatosPersonales> listaPersonas= new ArrayList<DatosPersonales>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM DATOS_PERSONALES");

		while (rs.next()) {
		    int id = rs.getInt("ID");
		    String nombre = rs.getString("NOMBRES");
		    String apellido = rs.getString("APELLIDO");
		    int dni = rs.getInt("DNI");
		    DatosPersonales datos = new DatosPersonales(id, nombre, apellido, dni);
		    
		    listaPersonas.add(datos);
		}
		rs.close();
		stmt.close();
		return listaPersonas;
	}
	
	//retorna true si existe la persona ya
	@Override
	public boolean validarPersona(int id) throws SQLException{
		String sql= "SELECT COUNT(*) FROM DATOS_PERSONALES WHERE ID=?;";
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql);
    	boolean existe=false;   
  		pstmt.setInt(1,id);
        ResultSet rs=pstmt.executeQuery();
        if (rs.next()) {
        	int cant=rs.getInt(1);
        	if(cant>0) existe=true;
        }
        rs.close();
        pstmt.close();
		return existe;
	}
	
	public DatosPersonales buscarPorID(int id) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM DATOS_PERSONALES");
		DatosPersonales datos = null;
		while (rs.next()) {
			int aux=rs.getInt("ID");
			if(aux==id) {
			  String nombre = rs.getString("NOMBRES");
		      String apellido = rs.getString("APELLIDO");
		      int dni = rs.getInt("DNI");
		   
		    datos = new DatosPersonales(id, nombre, apellido, dni);
			}
		}
		rs.close();
		stmt.close();
		return datos;
	}
}