package data.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.domain.DatosPersonales;

public interface DatosPersonalesDAO {
	public boolean existeDNI(int dni) throws SQLException;
	public void cargarDatos(DatosPersonales nuevosDatos) throws SQLException;
	public ArrayList<DatosPersonales> listar() throws SQLException;
	public boolean validarPersona(int id) throws SQLException;
	public DatosPersonales buscarPorID(int id) throws SQLException;
	public int obtenerIdPorDNI(int dni) throws SQLException;
}