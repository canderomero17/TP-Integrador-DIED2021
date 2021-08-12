package TP_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Objetos.Trayecto;
import TP_Util.ConnectionPostgres;

public class TrayectoDAO_SQL implements TrayectoDAO {
	
	static Connection con = null;
	static PreparedStatement sentencia = null; 
	
	@SuppressWarnings("rawtypes")
	@Override
	public void guardarTrayecto(Trayecto t) {
		
		con = ConnectionPostgres.conectar();
		
		String SQL = "INSERT INTO trayecto (id,origen,destino,colorlinea, costototal, duraciontotal, distanciatotal) VALUES('" +t.getIdCamino()+"', '" +t.getOrigen().getId()+"','" +t.getDestino().getId()+ "','" +t.getColorLinea()+"', 0, 0, 0)";
		
		try {
			sentencia = con.prepareStatement(SQL);
			sentencia.executeUpdate();
			System.out.println("Datos cargados");
			JOptionPane.showMessageDialog(null, "Trayecto agregado con exito!");
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		finally {
			try {
				if(sentencia!=null) sentencia.close();
				if(con!= null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	  
	}

	@Override
	public ResultSet consultarDato(String a, String b) {
		
		String t = "SELECT * FROM trayecto WHERE "+a+" = '"+b+"'"; //obtengo el color de la linea
		
		Connection cn = ConnectionPostgres.conectar();
		Statement st = null;
		ResultSet datos = null;
		
		try {
			st = cn.createStatement();
			datos = st.executeQuery(t);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return datos;
	}

	
	@Override
	public void actualizarDatosTrayecto(String id, Double dist, Integer dur, Double costo) {
		
			con = ConnectionPostgres.conectar();
			
			String t = "UPDATE trayecto SET costototal = (SELECT sum(costototal)+"+costo+" FROM trayecto WHERE id = '"+id+"'),"
					+ "duraciontotal = (SELECT sum(duraciontotal)+"+dur+"FROM trayecto WHERE id = '"+id+"'),"
							+ "distanciatotal = (SELECT sum(distanciatotal)+"+dist+"FROM trayecto WHERE id = '"+id+"')"
									+ "WHERE id = '"+id+"'";
			 
			 
			try {
				sentencia = con.prepareStatement(t);
				sentencia.executeUpdate();
				System.out.println("Datos cargados");
	//		 	JOptionPane.showMessageDialog(null, "Trayecto actualizado con éxito!");
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
			
			finally {
				try {
					if(sentencia!=null) sentencia.close();
					if(con!= null) con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
	}
	
	

}
