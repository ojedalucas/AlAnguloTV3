package Data.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Domain.Resenia;

public interface ReseniaDAO {

	void cargarResenia(Resenia r) throws SQLException;
	
	public Resenia descargarResenia(int ID) throws SQLException;

	public ArrayList<Resenia> listarNoAprobadas() throws SQLException;
		
	public ArrayList<Resenia> listarAprobadas() throws SQLException;

	public boolean validarResenia(int ID) throws SQLException;
		
	public void eliminarResenia(int id) throws SQLException;

	void aprobarResenia(int iD) throws SQLException;

	public boolean existeResenia(int idUsuario, int idPelicula) throws SQLException;
}
