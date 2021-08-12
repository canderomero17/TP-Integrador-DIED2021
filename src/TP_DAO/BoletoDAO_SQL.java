package TP_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import Objetos.Boleto;
import TP_Util.ConnectionPostgres;

public class BoletoDAO_SQL implements BoletoDAO{
	
	static Connection con = null;
	static PreparedStatement sentencia = null; 

	
	public void registrarVenta(Boleto b) {
		 
		con = ConnectionPostgres.conectar();
		
		String SQL = "INSERT INTO ventaboleto VALUES('"+b.getNroBoleto()+"','"+b.getCorreoCliente()+"','"+b.getNombreCliente()+"','"+b.getFecha()+"','"+b.getEstacionOrigen()+"','"+b.getEstacionDestino()+"','"+b.getCaminoASeguir()+"','"+b.getCosto()+"')";
		
		try {
			sentencia = con.prepareStatement(SQL);
			sentencia.executeUpdate();
			System.out.println("Datos cargados");
			JOptionPane.showMessageDialog(null, "Compra realizada con exito!");
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		finally {
			try {
				if(sentencia != null) {
					sentencia.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

}
}
