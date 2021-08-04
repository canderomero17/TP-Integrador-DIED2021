package TP_Util;

import java.sql.*;

public class ConnectionPostgres {
	
	private static final String url = "jdbc:postgresql://localhost:5432/TpGrupoJava";
	private static final String user = "postgres";
	private static final String pass = "1234";
	
	public ConnectionPostgres() {
		
	}
	
	static Connection con = null;
	static Statement sentencia; 
	
	public static Connection conectar() {
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, pass);
			//sentencia = con.createStatement();
			//System.out.println("Conectado");
		} catch (Exception e) {
			System.out.println("No conectado");
		}
		
		return con;
	}
	
/*	public static ResultSet getTabla(String Consulta) {
		Connection cn = conectar();
		Statement st;
		ResultSet datos = null;
		
		try {
			st = cn.createStatement();
			datos = st.executeQuery(Consulta);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return datos;
	}
	*/

	/*
	 * public static void guardarEstacion (Estacion e) {
	 * String t = "INSERT INTO estaciones VALUES('" +e.getId()+"', '" +e.getNombre()+"','" +e.getHorarioApertura()+ "','" +e.getHorarioCierre()+ "','"+e.getEstado()+"')";
		
		try {
			sentencia.executeUpdate(t);
			System.out.println("Datos cargados");
		} catch(Exception ex) {
			System.out.println("Error");
		}
	}
	 */
		
	
/*
 * 	public static void eliminarEstacion(int x) {
 * 		String t = "DELETE FROM estaciones WHERE id = "+x;
		
		try {
			sentencia.executeUpdate(t);
			System.out.println("Estación eliminada");
		} catch(Exception ex) {
			System.out.println("Error");
		}
	}
 */
		

}
		
