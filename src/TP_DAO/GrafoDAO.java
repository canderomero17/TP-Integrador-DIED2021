package TP_DAO;

import java.sql.ResultSet;

public interface GrafoDAO {
	
	public ResultSet mostrarTodosLosDatos(String tablaConsulta);
	public ResultSet obtenerColor();
}
