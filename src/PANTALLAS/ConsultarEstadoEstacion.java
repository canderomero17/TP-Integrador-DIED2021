package PANTALLAS;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.ButtonGroup;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import TP_DAO.EstacionDAO_SQL;
import TP_DAO.EstacionesDAO;

@SuppressWarnings("serial")
public class ConsultarEstadoEstacion extends JDialog {

	private JTable table;
	private JTextField panelTextoOrden;
	private JTable table_1;
	private JRadioButton checkMantenimiento;
	private JRadioButton checkOperativa;
	private JLabel textoIngreseOrden;
	private JScrollPane js;
	private JScrollPane js_1;
	private JLabel textoObservacion;
	private JTextField panelTexto;
	private JButton botonCambiarEstado;
	private JRadioButton checkHistorial;
	private ResultSet result_1 = null;
	ButtonGroup buttongroup = new ButtonGroup();
	
	public ConsultarEstadoEstacion() {
		getContentPane().setBackground(new Color(255, 255, 204));
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkHistorial.setVisible(true);
				checkHistorial.setSelected(false);
				if(table.getValueAt(table.getSelectedRow(), 4).equals("Operativa")) {
					textoObservacion.setVisible(false);
					panelTexto.setVisible(false);
					checkMantenimiento.setVisible(true);
					checkMantenimiento.setSelected(false);
					checkOperativa.setVisible(false);
					js_1.setVisible(false);
					validate();
				}else {
					textoObservacion.setVisible(false);
					checkOperativa.setVisible(true);
					checkOperativa.setSelected(false);
					checkMantenimiento.setVisible(false);
					panelTextoOrden.setVisible(false);
					textoIngreseOrden.setVisible(false);
					validate();
				}
			}
		});
		
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(10, 34, 416, 50);
		
		js=new JScrollPane(table);
		js.setBounds(10, 45, 766, 101);
		js.setVisible(true);
		getContentPane().add(js);
		
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBounds(10, 212, 766, 130);
		
		js_1=new JScrollPane(table_1);
		js_1.setBounds(10, 224, 766, 126);
		js_1.setVisible(false);
		getContentPane().add(js_1);
		
		textoIngreseOrden = new JLabel("Ingrese el numero de orden para autorizar mantenimiento:");
		textoIngreseOrden.setForeground(new Color(102, 0, 0));
		textoIngreseOrden.setVisible(false);
		textoIngreseOrden.setFont(new Font("Tahoma", Font.BOLD, 14));
		textoIngreseOrden.setBounds(10, 181, 464, 32);
		getContentPane().add(textoIngreseOrden);
		
		panelTextoOrden = new JTextField();
		panelTextoOrden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				botonCambiarEstado.setVisible(true);
			}
		});
		panelTextoOrden.setVisible(false);
		panelTextoOrden.setBounds(484, 189, 173, 19);
		getContentPane().add(panelTextoOrden);
		panelTextoOrden.setColumns(10);
		validate();
		
		JLabel textoSeleccionarEstacion = new JLabel("Seleccione una estacion:");
		textoSeleccionarEstacion.setForeground(new Color(102, 0, 0));
		textoSeleccionarEstacion.setBackground(new Color(255, 255, 204));
		textoSeleccionarEstacion.setFont(new Font("Tahoma", Font.BOLD, 15));
		textoSeleccionarEstacion.setBounds(10, 11, 261, 23);
		getContentPane().add(textoSeleccionarEstacion);
		
		ResultSet result = null;
		//ResultSet result_1 = null;
		
		EstacionesDAO estacionDao = new EstacionDAO_SQL();
		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int fila, int columna) {
				if(columna < 0 && fila < 0) {
					return true;
				} else {
					return false;
				}
			}
		};
		DefaultTableModel model_1 = new DefaultTableModel() {
			public boolean isCellEditable(int fila, int columna) {
				if(columna < 0 && fila < 0) {
					return true;
				} else {
					return false;
				}
			}
		};
		model.setColumnIdentifiers(new Object[] {"ID","Nombre","Horario de Apertura", "Horario de cierre", "Estado"});	
		model_1.setColumnIdentifiers(new Object[] {"Registro estado","ID estacion","Fecha de Inicio", "Fecha de Fin", "Observaciones"});
		
		result = estacionDao.mostrarTodosLosDatos("estaciones");
		
		try {
			while(result.next()) {
				model.addRow(new Object [] {result.getString("id"), result.getString("nombre"), result.getString("horarioap"),result.getString("horariocierre"), result.getString("estado")});
			}
			table.setModel(model);
		}catch(Exception exc){
			exc.printStackTrace();
			}
		
		checkMantenimiento = new JRadioButton("Iniciar tarea de mantenimiento");
		checkMantenimiento.setForeground(new Color(102, 0, 0));
		checkMantenimiento.setBackground(new Color(255, 255, 204));
		checkMantenimiento.setVisible(false);
		checkMantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				js_1.setVisible(false);
				textoObservacion.setVisible(true);
				panelTexto.setVisible(true);
				checkMantenimiento.setSelected(false);
				panelTextoOrden.setVisible(true);
				textoIngreseOrden.setVisible(true);
			}
		});
		checkMantenimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		checkMantenimiento.setBounds(209, 153, 283, 21);
		getContentPane().add(checkMantenimiento);
		buttongroup.add(checkMantenimiento);
		
		checkOperativa = new JRadioButton("Finalizar tarea de mantenimiento");
		checkOperativa.setForeground(new Color(102, 0, 0));
		checkOperativa.setBackground(new Color(255, 255, 204));
		checkOperativa.setVisible(false);
		checkOperativa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//int aux = (int) table.getValueAt(table.getSelectedRow(), 0);
				//String idEstacion = String.valueOf(aux);
				
				String idEstacion = "";
				if(table.getSelectedRow() != -1) {
					idEstacion = (String) model.getValueAt(table.getSelectedRow(), 0);
				}
				
				result_1 = estacionDao.consultarDatos("estadoestacion", "id", idEstacion);
				try {
					while(result_1.next()) {
						model_1.addRow(new Object [] {result_1.getInt("numregistroestado"), result_1.getString("id"), result_1.getString("fechainicio"),result_1.getString("fechafin"), result_1.getString("observacion")});
					}
					table_1.setModel(model_1);
					
				}catch(Exception exc){
					exc.printStackTrace();
					}
				
				textoObservacion.setVisible(true);
				panelTexto.setVisible(true);
				checkOperativa.setSelected(false);
				botonCambiarEstado.setVisible(true);
				js_1.setVisible(true);
				validate();
			}
		});
		checkOperativa.setFont(new Font("Tahoma", Font.BOLD, 14));
		checkOperativa.setBounds(515, 153, 261, 21);
		getContentPane().add(checkOperativa);
		buttongroup.add(checkOperativa);
		
		textoObservacion = new JLabel("Agregar observaciones (Opcional):");
		textoObservacion.setForeground(new Color(102, 0, 0));
		textoObservacion.setVisible(false);
		textoObservacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		textoObservacion.setBounds(10, 361, 283, 21);
		getContentPane().add(textoObservacion);
		
		panelTexto = new JTextField();
		panelTexto.setVisible(false);
		panelTexto.setDropMode(DropMode.INSERT);
		panelTexto.setBounds(10, 393, 766, 66);
		getContentPane().add(panelTexto);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBackground(new Color(102, 0, 0));
		botonCancelar.setForeground(new Color(255, 255, 204));
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				dispose();
			}
		});
		botonCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
		botonCancelar.setBounds(601, 470, 173, 30);
		getContentPane().add(botonCancelar);
		
		botonCambiarEstado = new JButton("Cambiar Estado");
		botonCambiarEstado.setBackground(new Color(102, 0, 0));
		botonCambiarEstado.setForeground(new Color(255, 255, 204));
		botonCambiarEstado.setVisible(false);
		botonCambiarEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String obs = panelTexto.getText();
				
				String row = "";  
				 
				if(checkMantenimiento.isSelected()) {
					int numMantenimiento = Integer.parseInt(panelTextoOrden.getText()); //numero unico para el cambio de estado
					if(table.getSelectedRow() != -1) {
						row = (String) model.getValueAt(table.getSelectedRow(), 0);
					}
					String estado = "En mantenimiento";
					estacionDao.tablaCambiarEstados(numMantenimiento, row, estado, obs);
					
					ResultSet result;
					result = estacionDao.mostrarTodosLosDatos("estaciones");
					model.setRowCount(0);
					try {
						while(result.next()) {
							model.addRow(new Object [] {result.getString("id"), result.getString("nombre"), result.getString("horarioap"),result.getString("horariocierre"), result.getString("estado")});
						}
						table.setModel(model);
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					
				}else if(checkOperativa.isSelected()) {
					int numEstado = 0;
					if(table_1.getSelectedRow() != -1) {
						row = (String) model_1.getValueAt(table_1.getSelectedRow(), 1);
						numEstado = (int) model_1.getValueAt(table_1.getSelectedRow(), 0);
					}
					String estado = "Operativa";
					estacionDao.tablaCambiarEstados(numEstado, row, estado, obs);
					
					ResultSet result;
					result = estacionDao.mostrarTodosLosDatos("estaciones");
					model.setRowCount(0);
					try {
						while(result.next()) {
							model.addRow(new Object [] {result.getString("id"), result.getString("nombre"), result.getString("horarioap"),result.getString("horariocierre"), result.getString("estado")});
						}
						table.setModel(model);
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					
				}else {
					JOptionPane.showConfirmDialog(null, "Debe seleccionar una opcion","Aviso Importante", JOptionPane.WARNING_MESSAGE);
				}
				
				limpiarFormulario();
		
			}
		});
		botonCambiarEstado.setFont(new Font("Tahoma", Font.BOLD, 16));
		botonCambiarEstado.setBounds(418, 470, 173, 30);
		getContentPane().add(botonCambiarEstado);
		
		checkHistorial = new JRadioButton("Mostrar historial");
		checkHistorial.setBackground(new Color(255, 255, 204));
		checkHistorial.setForeground(new Color(102, 0, 0));
		checkHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String idEstacion = (String) table.getValueAt(table.getSelectedRow(), 0);
				
				js_1.setVisible(true);
				panelTextoOrden.setVisible(false);
				panelTexto.setVisible(false);
				textoObservacion.setVisible(false);
				textoIngreseOrden.setVisible(false);
				
				result_1 = estacionDao.consultarDatos("estadoestacion", "id", idEstacion);
				try {
					while(result_1.next()) {
						model_1.addRow(new Object [] {result_1.getInt("numregistroestado"), result_1.getString("id"), result_1.getString("fechainicio"),result_1.getString("fechafin"), result_1.getString("observacion")});
					}
					table_1.setModel(model_1);
					
				}catch(Exception exc){
					exc.printStackTrace();
					}
				
			}
		});
		checkHistorial.setVisible(false);
		buttongroup.add(checkHistorial);
		checkHistorial.setFont(new Font("Tahoma", Font.BOLD, 14));
		checkHistorial.setBounds(10, 153, 173, 21);
		getContentPane().add(checkHistorial);
		
		validate();
	}
	
	public void limpiarFormulario() {
		textoObservacion.setVisible(false);
		checkHistorial.setVisible(false);
		checkMantenimiento.setVisible(false);
		checkOperativa.setVisible(false);
		panelTexto.setVisible(false);
		js_1.setVisible(false);
		textoIngreseOrden.setVisible(false);
		panelTextoOrden.setVisible(false);
		buttongroup.clearSelection();
	}
}
