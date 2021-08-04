package TP_DAO;

import java.sql.ResultSet;

import javax.swing.JTable;

import Objetos.Linea;

public interface LineasDAO {
	
	public Linea guardarLinea(Linea l);
	
	public ResultSet getTabla(String Consulta);
	
	public void EliminarLineas(String c);
	
	public void editarLineas(JTable t);
	
	public ResultSet consultarDatos(String a, String b);

}
