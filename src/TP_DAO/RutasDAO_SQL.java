package TP_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Objetos.Ruta;
import TP_Util.ConnectionPostgres;

public class RutasDAO_SQL implements RutasDAO {
	
	static Connection con = null;
	static PreparedStatement sentencia = null; 
	static PreparedStatement sentencia1 = null; 

	@Override
	public ResultSet getTabla(String Consulta) {
		Connection cn = ConnectionPostgres.conectar();
		Statement st = null;
		ResultSet datos = null;
		
		try {
			st = cn.createStatement();
			datos = st.executeQuery(Consulta);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return datos;
	}

	 @Override
	 	public void guardarRuta(Ruta ruta, String idTray) {
		 
		int numero = (int) ((Math.random()*10000)+1);
		 
		con = ConnectionPostgres.conectar();
		
		String SQL = "INSERT INTO rutas VALUES('"+ruta.getId()+" "+numero+"','"+ruta.getOrigen().getId()+"','"+ruta.getDestino().getId()+"','"+ruta.getDistancia()+"','"+ruta.getDuracionViaje()+"','"+ruta.getCantMaxPasajeros()+"','"+ruta.getEstado()+"','"+ruta.getCosto()+"')";
		String SQL1 = "INSERT INTO tramotrayecto (idgeneral,idruta,idtrayecto) VALUES ('"+numero+"', '"+ruta.getId()+" "+numero+"','"+idTray+"')";
		
		try {
			sentencia = con.prepareStatement(SQL);
			sentencia.executeUpdate();
			sentencia1 = con.prepareStatement(SQL1);
			sentencia1.executeUpdate();
			System.out.println("Datos cargados");
			JOptionPane.showMessageDialog(null, "Ruta agregada con exito!");
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		finally {
			try {
				if(sentencia!=null) sentencia.close();
				if(sentencia1!=null) sentencia1.close();
				if(con!= null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	} 

}
