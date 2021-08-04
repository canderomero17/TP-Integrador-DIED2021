package TP_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Objetos.Linea;
import TP_Util.ConnectionPostgres;

public class LineasDAO_SQL implements LineasDAO {

	static Connection con = null;
	static PreparedStatement sentencia = null; 
	
	@Override
	public Linea guardarLinea(Linea l) {
		
		con = ConnectionPostgres.conectar();
		
		String t = "INSERT INTO lineasdetransporte VALUES('" +l.getColor()+"', '" +l.getNombre()+"','" +l.getEstado()+"')";
		
		
		try {
			sentencia = con.prepareStatement(t);
			sentencia.executeUpdate();
			System.out.println("Datos cargados");
			JOptionPane.showMessageDialog(null, "Línea agregada con exito!");
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
		
		return l;
	}
	
	@Override
	public void EliminarLineas(String c) {
		
		con = ConnectionPostgres.conectar();
		
		String t = "DELETE FROM lineasdetransporte WHERE color = '"+c+"'";
		
		try {
			sentencia = con.prepareStatement(t);
			sentencia.executeUpdate();
			System.out.println("Linea eliminada");
			JOptionPane.showMessageDialog(null, "Estación eliminada con exito!");
		} catch(Exception ex) {
			System.out.println("Error");
		}
		
		finally {
			try {
				if(sentencia!=null) {
					sentencia.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	
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
	public void editarLineas(JTable t) {
		con = ConnectionPostgres.conectar();
		String SQL = "UPDATE lineasdetransporte SET nombre='"+t.getValueAt(0, 1)+"', estado='"+t.getValueAt(0, 2)+"' WHERE color = '"+t.getValueAt(0, 0)+"'";
		
		try {
			sentencia = con.prepareStatement(SQL);
			sentencia.executeUpdate();
			JOptionPane.showMessageDialog(null, "Linea editada con éxito!");
		
		}catch(SQLException ex) {
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
	
	
	@Override
	public ResultSet consultarDatos(String a, String b) {
		String t = "SELECT * FROM lineasdetransporte WHERE "+a+" = '"+b+"'";
	
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
	
	

}
