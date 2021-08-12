package PANTALLAS;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Objetos.Estacion;
import Objetos.Ruta;
import Objetos.Trayecto;
import TP_DAO.EstacionDAO_SQL;
import TP_DAO.EstacionesDAO;
import TP_DAO.RutasDAO;
import TP_DAO.RutasDAO_SQL;
import TP_DAO.TrayectoDAO;
import TP_DAO.TrayectoDAO_SQL;
import TP_Util.ConnectionPostgres;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList; 
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Color; 

@SuppressWarnings("serial")
public class RegistrarRuta extends JDialog {
	private JTextField IngresoTextoColor;
	private JTable table;
	private JTextField IngresoTextoDistancia;
	private JTextField IngresoTextoDuracion;
	private JTextField IngresoTextoPasajeros;
	private JTextField IngresoTextoCosto;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel textoOrigen;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxOrigen;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxDestino;
	private JRadioButton estadoActiva;
	private JRadioButton estadoNoActiva;
	private JButton guardarTramo;
	JRadioButton BotonAgregarTrayecto;
	JRadioButton BotonAgregarRuta;
	JPanel panel;
	JPanel panel_1;
	private JLabel lblNewLabel_13;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxOrigen1;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxDestino1;
	private JLabel lblNewLabel_14;
	private JTextField IngresoTextoIdTrayecto;
	private JButton guardarTrayecto;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxRutaTrayecto;
	private JLabel textoAsignarTrayectoRuta;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_4;
	private ButtonGroup grupo = new ButtonGroup();
	private DefaultTableModel modelo;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RegistrarRuta() {
		getContentPane().setBackground(new Color(255, 255, 204));
		 
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		
		lblNewLabel_1 = new JLabel("Ingrese el COLOR de la l\u00EDnea que desea buscar");
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 42, 342, 16);
		getContentPane().add(lblNewLabel_1);
		
		IngresoTextoColor = new JTextField();
		IngresoTextoColor.setBounds(359, 42, 124, 30);
		getContentPane().add(IngresoTextoColor);
		IngresoTextoColor.setColumns(10);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				BotonAgregarTrayecto.setVisible(true);
				BotonAgregarRuta.setVisible(true);
				
			}
		});
		table.setBounds(48, 98, 333, 78);
		 
		JScrollPane js=new JScrollPane(table);
        js.setBounds(10, 83, 764, 48);
        js.setVisible(true);
        getContentPane().add(js);
        
        
        modelo = new DefaultTableModel() {
			public boolean isCellEditable(int filas, int columnas) {
				if(columnas<0 && filas<0) {
					return true;
				} else {
					return false;
				}
			};
		};
		
		btnNewButton = new JButton("Consultar");
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RutasDAO rDAO = new RutasDAO_SQL();
				
				String color = IngresoTextoColor.getText();
				ResultSet rs = rDAO.getTabla("SELECT * FROM lineasdetransporte WHERE color = '"+color+"'");
				modelo.setColumnIdentifiers(new Object[] {"color", "nombre", "estado"});
				
				try {
					while(rs.next()) {
						modelo.addRow(new Object[] {rs.getString("color"), rs.getString("nombre"), rs.getString("estado")});
					}
					table.setModel(modelo);
					
				} catch(Exception ex) {
					System.out.println(ex);
				}
				
			}
		});
		btnNewButton.setBounds(507, 46, 124, 23);
		getContentPane().add(btnNewButton);
		
 		btnNewButton_2 = new JButton("Cancelar");
 		btnNewButton_2.setForeground(new Color(255, 255, 204));
 		btnNewButton_2.setBackground(new Color(102, 0, 0));
 		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(IngresoTextoColor.getText() == "") {
					limpiarFormulario1();
				} else {
					limpiarFormulario();
				}
			
				dispose();
			}
		});
		btnNewButton_2.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton_2);  
		
		lblNewLabel_2 = new JLabel("para luego registrarle un trayecto: ");
		lblNewLabel_2.setForeground(new Color(102, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 61, 339, 16);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Consultar L\u00EDnea");
		lblNewLabel_3.setForeground(new Color(102, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_3.setBounds(10, 11, 600, 25);
		getContentPane().add(lblNewLabel_3);
		
		BotonAgregarTrayecto = new JRadioButton("AGREGAR TRAYECTO");
		BotonAgregarTrayecto.setBackground(new Color(255, 255, 204));
		BotonAgregarTrayecto.setForeground(new Color(102, 0, 0));
		BotonAgregarTrayecto.setFont(new Font("Tahoma", Font.BOLD, 14));
		BotonAgregarTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				
				String colorLinea = "";
				if(table.getSelectedRow() != -1) {
					colorLinea = (String) modelo.getValueAt(table.getSelectedRow(), 0);
				}
				
				TrayectoDAO tDAO = new TrayectoDAO_SQL();
				
				ResultSet result = tDAO.consultarDato("colorlinea", colorLinea);
				
				String r = "";
				
				try {
					while(result.next()) {
						r = result.getString("colorlinea");
					}
					if(r.equals(colorLinea)) {
						panel_1.setVisible(false);
						JOptionPane.showInternalMessageDialog(null, "Ya existe trayecto asociado a la linea "+colorLinea);
					} else {
						panel_1.setVisible(true);
					}
				}catch(Exception exc) {
					 exc.printStackTrace();
				}
			}
		});
		BotonAgregarTrayecto.setBounds(193, 132, 194, 23);
		getContentPane().add(BotonAgregarTrayecto);
		BotonAgregarTrayecto.setVisible(false);
		
		comboBoxRutaTrayecto = new JComboBox();
		comboBoxRutaTrayecto.setBounds(483, 182, 124, 24);
		getContentPane().add(comboBoxRutaTrayecto);
		comboBoxRutaTrayecto.setVisible(false); 
		
		BotonAgregarRuta = new JRadioButton("AGREGAR RUTA");
		BotonAgregarRuta.setBackground(new Color(255, 255, 204));
		BotonAgregarRuta.setForeground(new Color(102, 0, 0));
		BotonAgregarRuta.setFont(new Font("Tahoma", Font.BOLD, 14));
		BotonAgregarRuta.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unused" })
			public void actionPerformed(ActionEvent e) {
				
				TrayectoDAO tDAO = new TrayectoDAO_SQL();
				panel_1.setVisible(false);
				
				String colorLinea = (String) table.getValueAt(table.getSelectedRow(), 0);
				  
				String s = "";
				String s1 = "";
				
				//p completar el comboBox de proyectos
				try {
					Connection cn = ConnectionPostgres.conectar();
					Statement st = cn.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM trayecto WHERE colorlinea = '"+colorLinea+"'");
					while(rs.next()) {
						s = rs.getString("id");
						s1 = rs.getString("colorlinea");
						//comboBoxRutaTrayecto.addItem(s);
					}
					if(s1.equals(colorLinea))  {
						panel.setVisible(true);
						textoAsignarTrayectoRuta.setVisible(true);
						comboBoxRutaTrayecto.setVisible(true);
						comboBoxRutaTrayecto.addItem(s);
					} else {
						JOptionPane.showInternalMessageDialog(null, "La linea no tiene registrado ningun trayecto para poder ingresarle una ruta");
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
				
			}
		});
		BotonAgregarRuta.setBounds(397, 132, 165, 23);
		getContentPane().add(BotonAgregarRuta);
		BotonAgregarRuta.setVisible(false);
		
		ButtonGroup grupo1 = new ButtonGroup();
		grupo1.add(BotonAgregarTrayecto);
		grupo1.add(BotonAgregarRuta);
		
  		textoAsignarTrayectoRuta = new JLabel("Seleccione el trayecto para el cual va a ingresar una ruta:");
  		textoAsignarTrayectoRuta.setForeground(new Color(102, 0, 0));
		textoAsignarTrayectoRuta.setFont(new Font("Tahoma", Font.BOLD, 14));
		textoAsignarTrayectoRuta.setBounds(10, 183, 452, 23);
		getContentPane().add(textoAsignarTrayectoRuta);
		textoAsignarTrayectoRuta.setVisible(false);    
		
   		panel = new JPanel();
  		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(10, 216, 764, 243);
		getContentPane().add(panel);
		panel.setLayout(null);  
		panel.setVisible(false);            
		
		lblNewLabel = new JLabel("Registrar Ruta");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setBounds(10, 11, 600, 45);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		textoOrigen = new JLabel("Estaci\u00F3n origen:");
		textoOrigen.setForeground(new Color(102, 0, 0));
		textoOrigen.setBounds(83, 72, 170, 23);
		panel.add(textoOrigen);
		textoOrigen.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		comboBoxOrigen = new JComboBox();
		comboBoxOrigen.setBounds(263, 73, 103, 24);
		panel.add(comboBoxOrigen);
		
		lblNewLabel_6 = new JLabel("Distancia (en km): ");
		lblNewLabel_6.setForeground(new Color(102, 0, 0));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(83, 106, 170, 23);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Duraci\u00F3n del viaje:");
		lblNewLabel_7.setForeground(new Color(102, 0, 0));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(393, 106, 169, 23);
		panel.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Cantidad m\u00E1xima de pasajeros:");
		lblNewLabel_8.setForeground(new Color(102, 0, 0));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(25, 140, 228, 23);
		panel.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Estado:");
		lblNewLabel_9.setForeground(new Color(102, 0, 0));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_9.setBounds(83, 174, 170, 23);
		panel.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Costo:");
		lblNewLabel_10.setForeground(new Color(102, 0, 0));
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10.setBounds(393, 140, 169, 23);
		panel.add(lblNewLabel_10);
		
		IngresoTextoDistancia = new JTextField();
		IngresoTextoDistancia.setBounds(263, 108, 103, 20);
		panel.add(IngresoTextoDistancia);
		IngresoTextoDistancia.setColumns(10);
		
		IngresoTextoDuracion = new JTextField();
		IngresoTextoDuracion.setBounds(572, 109, 103, 20);
		panel.add(IngresoTextoDuracion);
		IngresoTextoDuracion.setColumns(10);
		
		IngresoTextoPasajeros = new JTextField();
		IngresoTextoPasajeros.setBounds(264, 143, 102, 20);
		panel.add(IngresoTextoPasajeros);
		IngresoTextoPasajeros.setColumns(10);
		
		IngresoTextoCosto = new JTextField();
		IngresoTextoCosto.setBounds(572, 143, 103, 20);
		panel.add(IngresoTextoCosto);
		IngresoTextoCosto.setColumns(10);
		
		estadoActiva = new JRadioButton("Activa");
		estadoActiva.setForeground(new Color(102, 0, 0));
		estadoActiva.setFont(new Font("Tahoma", Font.BOLD, 14));
		estadoActiva.setBackground(new Color(255, 255, 204));
		estadoActiva.setBounds(263, 176, 90, 23);
		panel.add(estadoActiva);
		grupo.add(estadoActiva);
		
		estadoNoActiva = new JRadioButton("No activa");
		estadoNoActiva.setFont(new Font("Tahoma", Font.BOLD, 14));
		estadoNoActiva.setBackground(new Color(255, 255, 204));
		estadoNoActiva.setForeground(new Color(102, 0, 0));
		estadoNoActiva.setBounds(355, 176, 103, 23);
		panel.add(estadoNoActiva);
		grupo.add(estadoNoActiva);
		
		lblNewLabel_5 = new JLabel("Estaci\u00F3n destino:");
		lblNewLabel_5.setForeground(new Color(102, 0, 0));
		lblNewLabel_5.setBounds(393, 72, 170, 23);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		comboBoxDestino = new JComboBox();
		comboBoxDestino.setBounds(573, 73, 103, 24);
		panel.add(comboBoxDestino);
		
		lblNewLabel_4 = new JLabel("(en minutos)");
		lblNewLabel_4.setForeground(new Color(102, 0, 0));
		lblNewLabel_4.setBounds(403, 125, 84, 14);
		panel.add(lblNewLabel_4);
		
		guardarTramo = new JButton("Guardar");
		guardarTramo.setBackground(new Color(102, 0, 0));
		guardarTramo.setForeground(new Color(255, 255, 204));
		guardarTramo.setFont(new Font("Tahoma", Font.BOLD, 16));
		guardarTramo.setBounds(646, 202, 108, 30);
		panel.add(guardarTramo);
		guardarTramo.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				
				ResultSet r = null;
				ResultSet r1 = null;
				RutasDAO rutasDao = new RutasDAO_SQL();
				TrayectoDAO trayectoDao = new TrayectoDAO_SQL();
				EstacionesDAO estacionDao = new EstacionDAO_SQL();
				
				RutasDAO rDAO = new RutasDAO_SQL();
				TrayectoDAO tDAO = new TrayectoDAO_SQL();
				
				ArrayList<String> listEstacionOrigen = new ArrayList<String>();
				ArrayList<String> listEstacionDestino = new ArrayList<String>();
				
				String estOrigen = comboBoxOrigen.getSelectedItem().toString();
				String estDest = comboBoxDestino.getSelectedItem().toString(); 
				
				Double dist = Double.parseDouble(IngresoTextoDistancia.getText());
				Integer duracion = Integer.parseInt(IngresoTextoDuracion.getText());
				Integer cantPasajeros = Integer.parseInt(IngresoTextoPasajeros.getText());
				Double costo = Double.parseDouble(IngresoTextoCosto.getText());
				
				String posibleEstado;
				if(estadoActiva.isSelected()) posibleEstado = "Activa";
				else posibleEstado = "No activa"; 
				  
				String idTray = comboBoxRutaTrayecto.getSelectedItem().toString();
				
				r = estacionDao.consultarDatos("estaciones","id", estOrigen);
				r1 = estacionDao.consultarDatos("estaciones", "id", estDest);
				try {
					while(r.next()) {
						listEstacionOrigen.add(r.getString("id"));
						listEstacionOrigen.add(r.getString("nombre"));
						listEstacionOrigen.add(r.getString("horarioap"));
						listEstacionOrigen.add(r.getString("horariocierre"));
						listEstacionOrigen.add(r.getString("estado"));
						}
					while(r1.next()) {
						listEstacionDestino.add(r1.getString("id"));
						listEstacionDestino.add(r1.getString("nombre"));
						listEstacionDestino.add(r1.getString("horarioap"));
						listEstacionDestino.add(r1.getString("horariocierre"));
						listEstacionDestino.add(r1.getString("estado"));
					}
					}catch(Exception exc){
						exc.printStackTrace();
					 }
				
				Estacion nuevaEstacionOrigen = new Estacion(listEstacionOrigen.get(0), listEstacionOrigen.get(1), listEstacionOrigen.get(2), listEstacionOrigen.get(3), listEstacionOrigen.get(4));
				Estacion nuevaEstacionDestino = new Estacion(listEstacionDestino.get(0), listEstacionDestino.get(1), listEstacionDestino.get(2), listEstacionDestino.get(3), listEstacionDestino.get(4));
	
				Ruta nuevaRuta = new Ruta("Ruta "+estOrigen+estDest, nuevaEstacionOrigen, nuevaEstacionDestino, dist, duracion, cantPasajeros, posibleEstado, costo);
				
			  	rDAO.guardarRuta(nuevaRuta, idTray);
			 	
			 	//ahora actualizo los datos de la tabla proyecto
				 
	 			tDAO.actualizarDatosTrayecto(idTray, dist, duracion, costo);
				 
	 			limpiarFormulario();
			}
		});
		
		
		
    	panel_1 = new JPanel();
  	 	panel_1.setBackground(new Color(255, 255, 204));
		panel_1.setBounds(10, 219, 740, 231);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);  
		panel_1.setVisible(false);       
		
		lblNewLabel_11 = new JLabel("Agregar Trayecto");
		lblNewLabel_11.setForeground(new Color(102, 0, 0));
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_11.setBounds(10, 11, 231, 30);
		panel_1.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Estaci\u00F3n origen:");
		lblNewLabel_12.setForeground(new Color(102, 0, 0));
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_12.setBounds(10, 76, 231, 19);
		panel_1.add(lblNewLabel_12);
		
		comboBoxDestino1 = new JComboBox();
		comboBoxDestino1.setBounds(272, 76, 180, 23);
		panel_1.add(comboBoxDestino1);
		
		lblNewLabel_13 = new JLabel("Estaci\u00F3n destino:");
		lblNewLabel_13.setForeground(new Color(102, 0, 0));
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_13.setBounds(10, 111, 231, 16);
		panel_1.add(lblNewLabel_13);
		
		comboBoxOrigen1 = new JComboBox();
		comboBoxOrigen1.setBounds(272, 110, 180, 23);
		panel_1.add(comboBoxOrigen1);
		
		lblNewLabel_14 = new JLabel("Ingrese un ID para el trayecto:");
		lblNewLabel_14.setForeground(new Color(102, 0, 0));
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_14.setBounds(10, 153, 231, 23);
		panel_1.add(lblNewLabel_14);
		
		IngresoTextoIdTrayecto = new JTextField();
		IngresoTextoIdTrayecto.setBounds(272, 155, 180, 23);
		panel_1.add(IngresoTextoIdTrayecto);
		IngresoTextoIdTrayecto.setColumns(10);
		
		guardarTrayecto = new JButton("Guardar");
		guardarTrayecto.setForeground(new Color(255, 255, 204));
		guardarTrayecto.setBackground(new Color(102, 0, 0));
		guardarTrayecto.setFont(new Font("Tahoma", Font.BOLD, 16));
		guardarTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					TrayectoDAO tDAO = new TrayectoDAO_SQL();
				
					String estOrigen = comboBoxDestino1.getSelectedItem().toString();
					String estDestino = comboBoxOrigen1.getSelectedItem().toString();
					Estacion nuevaEstacionOrigen = new Estacion(estOrigen);
					Estacion nuevaEstacionDestino = new Estacion(estDestino);
					String id =  IngresoTextoIdTrayecto.getText();
					String colorL = table.getValueAt(table.getSelectedRow(), 0).toString();
					
					Trayecto nuevoTray = new Trayecto(id,colorL, nuevaEstacionOrigen, nuevaEstacionDestino);
					
					tDAO.guardarTrayecto(nuevoTray);
					
					limpiarFormulario();
			}
		});
		guardarTrayecto.setBounds(622, 190, 108, 30);
		panel_1.add(guardarTrayecto);
		panel_1.setVisible(false); 
		
		//p cmpletar los comboBox del panel
		try {
			Connection cn = ConnectionPostgres.conectar();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM estaciones ORDER BY id");
			while(rs.next()) {
				this.comboBoxOrigen.addItem(rs.getString("id"));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		try {
			Connection cn = ConnectionPostgres.conectar();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM estaciones ORDER BY id");
			while(rs.next()) {
				this.comboBoxDestino.addItem(rs.getString("id"));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		//p completar los comboBox del panel_1 
		try {
			Connection cn = ConnectionPostgres.conectar();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM estaciones ORDER BY id");
			while(rs.next()) {
				this.comboBoxDestino1.addItem(rs.getString("id"));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		try {
			Connection cn = ConnectionPostgres.conectar();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM estaciones ORDER BY id");
			while(rs.next()) {
				this.comboBoxOrigen1.addItem(rs.getString("id"));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		
	}
	
	public void limpiarFormulario1() {
		grupo.clearSelection();
		IngresoTextoColor.setText("");
		IngresoTextoCosto.setText("");
		IngresoTextoDistancia.setText("");
		IngresoTextoDuracion.setText("");
		IngresoTextoIdTrayecto.setText("");
		IngresoTextoPasajeros.setText("");
		textoAsignarTrayectoRuta.setVisible(false);
		comboBoxRutaTrayecto.setVisible(false);
		BotonAgregarRuta.setVisible(false);
		BotonAgregarTrayecto.setVisible(false);
		modelo.setRowCount(0); 
		panel.setVisible(false);
		panel_1.setVisible(false);
		comboBoxDestino.setSelectedIndex(0);
		comboBoxDestino1.setSelectedIndex(0);
		comboBoxOrigen.setSelectedIndex(0);
		comboBoxOrigen1.setSelectedIndex(0);
		comboBoxRutaTrayecto.setSelectedIndex(0);
	}
	
	public void limpiarFormulario() {
		grupo.clearSelection();
		IngresoTextoColor.setText("");
		IngresoTextoCosto.setText("");
		IngresoTextoDistancia.setText("");
		IngresoTextoDuracion.setText("");
		IngresoTextoIdTrayecto.setText("");
		IngresoTextoPasajeros.setText("");
		textoAsignarTrayectoRuta.setVisible(false);
		comboBoxRutaTrayecto.setVisible(false);
		BotonAgregarRuta.setVisible(false);
		BotonAgregarTrayecto.setVisible(false);
		modelo.setRowCount(0); 
		panel.setVisible(false);
		panel_1.setVisible(false);
		comboBoxDestino.setSelectedIndex(0);
		comboBoxDestino1.setSelectedIndex(0);
		comboBoxOrigen.setSelectedIndex(0);
		comboBoxOrigen1.setSelectedIndex(0); 
		comboBoxRutaTrayecto.removeAllItems();
	}
	
}
