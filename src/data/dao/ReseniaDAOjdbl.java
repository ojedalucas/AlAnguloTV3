package data.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import data.ConnectionManager;
import model.domain.Resenia;

public class ReseniaDAOjdbl implements ReseniaDAO {
	private Connection connection;
	
	public ReseniaDAOjdbl() {
		connection= ConnectionManager.getConnection();
	}
	
	@Override
	public void cargarResenia(Resenia r) throws SQLException {
		String fechaHora = r.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String sql = "INSERT INTO RESENIA (CALIFICACION, COMENTARIO, APROBADO, FECHA_HORA, ID_USUARIO, ID_PELICULA) " +
					"VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, r.getCalificacion());
		pstmt.setString(2, r.getComentario());
		pstmt.setBoolean(3, r.isAprobado());
		pstmt.setString(4, fechaHora);
		pstmt.setInt(5, r.getIdUsuario());
		pstmt.setInt(6, r.getIdPelicula());

		pstmt.executeUpdate();
		pstmt.close();
    }

	@Override
	public Resenia descargarResenia(int ID) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM RESENIA");
		Resenia datos = null;

		while (rs.next()) {
			int aux=rs.getInt("ID");
			if(aux==ID) {
			  int calificacion = rs.getInt("CALIFICACION");
		      String comentario = rs.getString("COMENTARIO");
		      boolean aprobado= rs.getBoolean("APROBADO");
		      String fechaString=rs.getString("FECHA_HORA");
		      LocalDateTime fechaHora = LocalDateTime.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		      int idUsuario = rs.getInt("ID_USUARIO");
		      int idPelicula = rs.getInt("ID_PELICULA");
		   
		    datos = new Resenia(aux,calificacion, comentario, aprobado, fechaHora, idUsuario, idPelicula);
			}
		}
		return datos;
	}
		
	@Override
	public ArrayList<Resenia> listarNoAprobadas() throws SQLException{
		Statement stmt = connection.createStatement();
		ArrayList<Resenia> listaResenias= new ArrayList<Resenia>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM RESENIA");

		while (rs.next()) {
			boolean aprobado= rs.getBoolean("APROBADO");
			if(!aprobado) {
		      int id = rs.getInt("ID");
		      int calificacion = rs.getInt("CALIFICACION");
     		  String comentario = rs.getString("COMENTARIO");
		      String fechaString=rs.getString("FECHA_HORA");
		      LocalDateTime fechaHora = LocalDateTime.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		      int idUsuario = rs.getInt("ID_USUARIO");
		      int idPelicula = rs.getInt("ID_PELICULA");
		      
		      Resenia datos = new Resenia(id, calificacion, comentario, aprobado, fechaHora, idUsuario, idPelicula);
		    
		    listaResenias.add(datos);
		    }
		}
		return listaResenias;
	}
	
	@Override
	public ArrayList<Resenia> listarAprobadas() throws SQLException{
		Statement stmt = connection.createStatement();
		ArrayList<Resenia> listaResenias= new ArrayList<Resenia>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM RESENIA");
		
		while (rs.next()) {
			boolean aprobado= rs.getBoolean("APROBADO");
			if(aprobado) {
		      int id = rs.getInt("ID");
		      int calificacion = rs.getInt("CALIFICACION");
     		  String comentario = rs.getString("COMENTARIO");
		      String fechaString=rs.getString("FECHA_HORA");
		      LocalDateTime fechaHora = LocalDateTime.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		      int idUsuario = rs.getInt("ID_USUARIO");
		      int idPelicula = rs.getInt("ID_PELICULA");
		      
		      Resenia datos = new Resenia(id, calificacion, comentario, aprobado, fechaHora, idUsuario, idPelicula);
		    
		    listaResenias.add(datos);
		    }
		}
		return listaResenias;
	}
	
	@Override
	public boolean validarResenia(int ID) throws SQLException {
		String sql= "SELECT COUNT(*) FROM RESENIA WHERE ID=?;";
		PreparedStatement pstmt;
    	pstmt = connection.prepareStatement(sql);
    	boolean existe=false;   
  		pstmt.setInt(1,ID);
        ResultSet rs=pstmt.executeQuery();
        int cant=rs.getInt(1);
        rs.close();
        if(cant>0) existe=true;
        pstmt.close();
		return existe;
	}

	@Override
	public void eliminarResenia(int id) throws SQLException{
        PreparedStatement pstmt;
   
		String sql = "DELETE FROM RESENIA WHERE ID=?;";
		pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1,id);
		pstmt.executeUpdate();   
		sql = "DELETE FROM sqlite_sequence WHERE name='RESENIA'";
		pstmt = connection.prepareStatement(sql);
		pstmt.executeUpdate();
	    pstmt.close();   			
    }
	
	@Override
	public void aprobarResenia(int ID) throws SQLException{
        PreparedStatement pstmt;
   
		String sql = "UPDATE RESENIA SET APROBADO= ? WHERE ID=?;";
		pstmt = connection.prepareStatement(sql);
		pstmt.setBoolean(1,true);
		pstmt.setInt(2,ID);
		pstmt.executeUpdate();   
	    pstmt.close();   	
		
    }

	@Override
	public boolean existeResenia(int idUsuario, int idPelicula) throws SQLException{
		String sql = "SELECT 1 FROM RESENIA WHERE ID_USUARIO=? AND ID_PELICULA=? LIMIT 1";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, idUsuario);
			pstmt.setInt(2, idPelicula);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		}
	}
}

