package TP_DAO;

import java.sql.ResultSet;

import TP.Ruta;

public interface RutasDAO {
	
	public ResultSet getTabla(String Consulta);
	
	public void guardarRuta(Ruta r, String idTray);

}
