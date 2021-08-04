package TP_DAO;

import java.sql.ResultSet;

import Objetos.Trayecto;

public interface TrayectoDAO {
	
	@SuppressWarnings("rawtypes")
	public void guardarTrayecto(Trayecto t);
	
	public ResultSet consultarDato(String a, String b);

	public void actualizarDatosTrayecto(String id, Double dist, Integer dur, Double costo);
}
