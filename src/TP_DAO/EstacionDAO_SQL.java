package TP_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Objetos.Estacion;
import TP_Util.ConnectionPostgres;

public class EstacionDAO_SQL implements EstacionesDAO {

	static Connection con = null;
	static PreparedStatement sentencia = null; 
	
	
	@Override
	public Estacion guardarEstacion(Estacion e) {
		con = ConnectionPostgres.conectar();
		
		String t = "INSERT INTO estaciones VALUES('" +e.getId()+"', '" +e.getNombre()+"','" +e.getHorarioApertura()+ "','" +e.getHorarioCierre()+ "','"+e.getEstado()+"')";
		
		
		try {
			sentencia = con.prepareStatement(t);
			sentencia.executeUpdate();
			System.out.println("Datos cargados");
			JOptionPane.showMessageDialog(null, "Estación agregada con exito!");
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
		
		return e;
		
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
	public void eliminarEstacion(String x) {
		
		con = ConnectionPostgres.conectar();
		
		String t = "DELETE FROM estaciones WHERE id = '"+x+"'";
		
		try {
			sentencia = con.prepareStatement(t);
			sentencia.executeUpdate();
			System.out.println("Estación eliminada");
			JOptionPane.showMessageDialog(null, "Estación eliminada con exito!");
		} catch(Exception ex) {
			System.out.println("Error");
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
	public void editarEstacion(JTable t) {
		
		con = ConnectionPostgres.conectar();
		String SQL = "UPDATE estaciones SET nombre='"+t.getValueAt(0, 1)+"', horarioap='"+t.getValueAt(0, 2)+"', horariocierre='"+t.getValueAt(0, 3)+"', estado='"+t.getValueAt(0, 4) + "' WHERE id ='"+t.getValueAt(0, 0)+"'";
		
		try {
			sentencia = con.prepareStatement(SQL);
			sentencia.executeUpdate();
			JOptionPane.showMessageDialog(null, "Estación editada con exito!");
		
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
	public ResultSet consultarDatos(String tabla, String a, String b) {
		
		String t = "SELECT * FROM "+tabla+" WHERE "+a+" = '"+b+"'";
	
		Connection cn = ConnectionPostgres.conectar();
		Statement st=null;
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
	public void tablaCambiarEstados(int numMantenimiento, String row, String estado, String obs) {
		PreparedStatement sentencia1 = null;
		
		String t = "UPDATE estaciones SET estado = '"+estado+"' WHERE id='"+row+"'";
		String t1 = "";
		if(estado.equals("En mantenimiento")) {
				t1 = "INSERT INTO estadoestacion (numregistroestado, id, fechainicio, observacion) VALUES('"+numMantenimiento+"','"+row+"','"+LocalDateTime.now()+"','"+obs+"')";
		}
		if(estado.equals("Operativa")) {
			t1 = "UPDATE estadoestacion SET fechafin = '"+LocalDateTime.now()+"', observacion ='"+obs+"' WHERE id = '" +row +"'";
		}
		
		con = ConnectionPostgres.conectar();
		try {
			sentencia1 = con.prepareStatement(t1);
			sentencia1.executeUpdate();
			sentencia = con.prepareStatement(t);
			sentencia.executeUpdate();
			JOptionPane.showMessageDialog(null, "Estado de estacion modifica", "Aviso Importante", JOptionPane.INFORMATION_MESSAGE);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "El estado de la estacion no se pudo modificar", "Aviso Importante", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
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

	@Override
	public ResultSet compararFechas() {
		
		String t = "SELECT est.id, estado.fechainicio, estado.fechafin FROM estaciones est LEFT JOIN estadoestacion estado ON est.id = estado.id ORDER BY estado.fechainicio nulls first";
		
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
	

}
