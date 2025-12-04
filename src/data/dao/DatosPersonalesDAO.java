package data.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.domain.DatosPersonales;

public interface DatosPersonalesDAO {
	//retorno true si existe
	boolean existeDNI(int dni) throws SQLException;

	void cargarDatos(DatosPersonales nuevosDatos) throws SQLException;

	ArrayList<DatosPersonales> listar() throws SQLException;

	boolean validarPersona(int id) throws SQLException;
	
	public DatosPersonales buscarPorID(int id) throws SQLException;

	public int obtenerIdPorDNI(int dni) throws SQLException;
}
