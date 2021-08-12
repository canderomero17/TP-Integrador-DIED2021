package TP_DAO;

import java.sql.ResultSet;

import javax.swing.JTable;

import Objetos.Estacion;

public interface EstacionesDAO {
	
	public Estacion guardarEstacion (Estacion e);
	
	public ResultSet getTabla(String Consulta);
	
	public void eliminarEstacion(String x);
	
	public void editarEstacion(JTable t);
	
	public ResultSet consultarDatos(String tabla, String a, String b);
	
	public void tablaCambiarEstados(int numMantenimiento, String row, String estado, String obs);
	
	public ResultSet mostrarTodosLosDatos(String tablaConsulta);
	
	public ResultSet compararFechas();

}
