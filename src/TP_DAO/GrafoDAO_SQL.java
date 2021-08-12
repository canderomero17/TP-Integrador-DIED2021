package TP_DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import TP_Util.ConnectionPostgres;

public class GrafoDAO_SQL implements GrafoDAO{

	static Connection con = null;
	
	@Override
	public ResultSet mostrarTodosLosDatos(String tablaConsulta) {
		String t = "SELECT * FROM "+tablaConsulta+" ORDER BY id";
		con = ConnectionPostgres.conectar();
		Statement sent = null;
		ResultSet datos = null;
		try {
			sent = con.createStatement();
			datos = sent.executeQuery(t);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return datos;
	}
	
	public ResultSet obtenerColor() {
		String t = "SELECT * FROM rutas r, lineasdetransporte lt, trayecto t, tramotrayecto tt WHERE r.id = tt.idruta AND tt.idtrayecto = t.id AND t.colorlinea = lt.color ORDER BY r.id";
		con = ConnectionPostgres.conectar();
		Statement sent=null;
		ResultSet datos = null;
		try {
			sent = con.createStatement();
			datos = sent.executeQuery(t);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return datos;
	
	}

}
