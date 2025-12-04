package data.dao;

import java.sql.*;
import java.util.ArrayList;

import data.ConnectionManager;
import model.domain.GeneroPelicula;
import model.domain.Pelicula;

public class PeliculaDAOjdbl implements PeliculaDAO {
	private Connection connection;

	public PeliculaDAOjdbl() {
		connection= ConnectionManager.getConnection();
	}
	public void cargarPelicula(Pelicula p) throws SQLException {
		String sql = "INSERT INTO PELICULA (GENERO, TITULO, RESUMEN, DIRECTOR, DURACION, RATINGPROMEDIO, ANIO, POSTER) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, p.getGenero().toString());
		pstmt.setString(2, p.getTitulo());
		pstmt.setString(3, p.getResumen());
		pstmt.setString(4, p.getDirector());
		pstmt.setDouble(5, p.getDuracion());
		pstmt.setFloat(6, p.getRatingPromedio());
		pstmt.setInt(7, p.getAnio());
		pstmt.setString(8, p.getPoster());

		pstmt.executeUpdate();

		ResultSet rs = pstmt.getGeneratedKeys();
    	if (rs.next()) {
        	int idGenerado = rs.getInt(1);
        	p.setId(idGenerado);
    	}
		rs.close();
		pstmt.close();
	}
	
	//todos los listar deben devolver la tabla completa(ordenada) con id, nombre, etc. 
	//para ordenar usa el comparator, anda a chequear que carajo es eso (lo pidieron en calse)
	
	@Override
	public ArrayList<Pelicula> listarPeliculas() throws SQLException {
		Statement stmt = connection.createStatement();
		ArrayList<Pelicula> listaPeliculas= new ArrayList<Pelicula>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PELICULA");

		while (rs.next()) {
		    int id = rs.getInt("ID");
		    String g = rs.getString("GENERO");
		    String titulo = rs.getString("TITULO");
		    String resumen = rs.getString("RESUMEN");
		    String director = rs.getString("DIRECTOR");
		    double duracion = rs.getDouble("DURACION");
            float ratingPromedio = rs.getFloat("RATINGPROMEDIO");
			int anio = rs.getInt("ANIO");
			String poster = rs.getString("POSTER");
		    
		    GeneroPelicula genero = GeneroPelicula.valueOf(g);
		    Pelicula datos = new Pelicula(id, genero, titulo, resumen, director, duracion, ratingPromedio, anio, poster);
		    
		    listaPeliculas.add(datos);
		}
		rs.close();
		stmt.close();
		return listaPeliculas;
	}

	//devuleva si una pelicula existe, pasandole un ID como parametro. si existe retorna true, si no false
	@Override
	public boolean validarPelicula(int id) throws SQLException{
			String sql= "SELECT COUNT(*) FROM PELICULA WHERE ID=?;";
			PreparedStatement pstmt;
	    	pstmt = connection.prepareStatement(sql);
	    	boolean existe=false;  
	  		pstmt.setInt(1,id);
	        ResultSet rs=pstmt.executeQuery();
	        int cant=rs.getInt(1);
	        rs.close();
	        if(cant>0) existe=true;
	        pstmt.close();
			return existe;
	}
	
	public Pelicula buscarPorID(int ID) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PELICULA");
        Pelicula pelicula = null;

        while (rs.next()) {
            int id=rs.getInt("ID");
            if(id==ID) {
              String g = rs.getString("GENERO");
              String titulo = rs.getString("TITULO");
              String resumen = rs.getString("RESUMEN");
              String director = rs.getString("DIRECTOR");
              double duracion = rs.getDouble("DURACION");
			  float ratingPromedio = rs.getFloat("RATINGPROMEDIO");
			  int anio = rs.getInt("ANIO");
			  String poster = rs.getString("POSTER");
		    
		      GeneroPelicula genero = GeneroPelicula.valueOf(g);
		      pelicula = new Pelicula(id, genero, titulo, resumen, director, duracion, ratingPromedio, anio, poster);
            }
        }
		rs.close();
		stmt.close();
        return pelicula;
    }

	public void eliminarTodas() throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM PELICULA");
		stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='PELICULA'");
		stmt.close();
	}

	public boolean baseVacia() throws SQLException {
    String sql = "SELECT 1 FROM PELICULA LIMIT 1";
    try (PreparedStatement pstmt = connection.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        return !rs.next();
    }
}
}


