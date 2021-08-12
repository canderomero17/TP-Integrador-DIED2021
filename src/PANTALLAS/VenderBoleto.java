package PANTALLAS;
 

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel; 
import javax.swing.table.DefaultTableModel; 
import EstructuraGRAFO.Grafo;
import EstructuraGRAFO.Vertice;
import Objetos.Boleto;
import Objetos.Estacion;
import TP_DAO.BoletoDAO;
import TP_DAO.BoletoDAO_SQL;
import TP_DAO.EstacionDAO_SQL;
import TP_DAO.EstacionesDAO; 
import TP_Util.ConnectionPostgres;

import javax.swing.JLabel;
import javax.swing.JOptionPane; 
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

@SuppressWarnings("serial")
public class VenderBoleto extends JDialog {

	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxEstacionDestino;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxEstacionOrigen;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTable table;
	private JScrollPane js;
	private JLabel lblNewLabel_6;
	private JTextField IngresoTextoNombre;
	private JTextField ingresoTextoCorreo;
	private JButton btnNewButton_3;
	private JPanel panel;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_1;
	private JRadioButton botonCaminoMasCorto;
	private JRadioButton botonCaminoMasBarato;
	private JRadioButton botonMasRapido;
	private ButtonGroup bg = new ButtonGroup();
	private DefaultTableModel model = new DefaultTableModel();

	private ArrayList<String> listEstacionOrigen = new ArrayList<String>();
	private ArrayList<String> listEstacionDestino = new ArrayList<String>();
	private JButton btnNewButton_4;
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VenderBoleto() {
		getContentPane().setBackground(new Color(255, 255, 204));
		
		Grafo grafo = new Grafo(); 
		grafo.armarGrafoEstacion();
		
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		{
			lblNewLabel = new JLabel("Comprar boleto");
			lblNewLabel.setForeground(new Color(102, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel.setBounds(10, 11, 600, 45);
			getContentPane().add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("Estaci\u00F3n origen:");
			lblNewLabel_1.setForeground(new Color(102, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1.setBounds(18, 62, 138, 19);
			getContentPane().add(lblNewLabel_1);
		}
		{
			comboBoxEstacionOrigen = new JComboBox();
			comboBoxEstacionOrigen.setBackground(new Color(102, 0, 0));
			comboBoxEstacionOrigen.setForeground(new Color(255, 255, 204));
			comboBoxEstacionOrigen.setBounds(166, 61, 108, 24);
			getContentPane().add(comboBoxEstacionOrigen);
		}
		{
			lblNewLabel_2 = new JLabel("Estaci\u00F3n destino:");
			lblNewLabel_2.setForeground(new Color(102, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_2.setBounds(284, 62, 138, 19);
			getContentPane().add(lblNewLabel_2);
		}
		{
			comboBoxEstacionDestino = new JComboBox();
			comboBoxEstacionDestino.setForeground(new Color(255, 255, 204));
			comboBoxEstacionDestino.setBackground(new Color(102, 0, 0));
			comboBoxEstacionDestino.setBounds(432, 61, 108, 24);
			getContentPane().add(comboBoxEstacionDestino);
		}
		
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[] {"Trayecto","Caminos"});
		
		JLabel lblNewLabel_3 = new JLabel("Posibles caminos para las estaciones seleccionadas:");
		lblNewLabel_3.setForeground(new Color(102, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(18, 92, 446, 25);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Filtrar posibles caminos por:");
		lblNewLabel_4.setForeground(new Color(102, 0, 0));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(18, 226, 446, 25);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_6 = new JLabel( );
		lblNewLabel_6.setForeground(new Color(102, 0, 0));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(461, 298, 118, 30);
		getContentPane().add(lblNewLabel_6);
		lblNewLabel_6.setVisible(false);
		
		botonMasRapido = new JRadioButton("M\u00E1s r\u00E1pido");
		botonMasRapido.setForeground(new Color(102, 0, 0));
		botonMasRapido.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonMasRapido.setBackground(new Color(255, 255, 204));
		botonMasRapido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Estacion nuevaEstacionOrigen = new Estacion(listEstacionOrigen.get(0), listEstacionOrigen.get(1), listEstacionOrigen.get(2), listEstacionOrigen.get(3), listEstacionOrigen.get(4));
				Estacion nuevaEstacionDestino = new Estacion(listEstacionDestino.get(0), listEstacionDestino.get(1), listEstacionDestino.get(2), listEstacionDestino.get(3), listEstacionDestino.get(4));
				 
				List<Vertice> listaCaminos = new ArrayList<Vertice>();
				ArrayList<String> caminosMostrar = new ArrayList<String>();
				listaCaminos = grafo.menorDuracion(nuevaEstacionOrigen, nuevaEstacionDestino);
				
				model.setRowCount(0);
			
				String mostrar ="";
				for(int i=0; i<listaCaminos.size(); i++) {
						caminosMostrar.add(listaCaminos.get(i).getEst().getId()+" --> ");
				}
					for(int t=0; t<caminosMostrar.size();t++) {
						mostrar += caminosMostrar.get(t)+" ";
					}
					model.addRow(new Object [] {"Trayecto "+(1),mostrar});
					caminosMostrar.clear();
					mostrar = ""; 
				table.setModel(model);
				
				lblNewLabel_5.setVisible(true);
				lblNewLabel_6.setVisible(true);
				btnNewButton_1.setVisible(true);
				lblNewLabel_6.setText(grafo.calcularPrecioBoleto(listaCaminos).toString()); 
				
			}
			
		});
		botonMasRapido.setBounds(79, 256, 124, 23);
		getContentPane().add(botonMasRapido);
		
		botonCaminoMasBarato = new JRadioButton("M\u00E1s barato");
		botonCaminoMasBarato.setBackground(new Color(255, 255, 204));
		botonCaminoMasBarato.setForeground(new Color(102, 0, 0));
		botonCaminoMasBarato.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonCaminoMasBarato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Estacion nuevaEstacionOrigen = new Estacion(listEstacionOrigen.get(0), listEstacionOrigen.get(1), listEstacionOrigen.get(2), listEstacionOrigen.get(3), listEstacionOrigen.get(4));
				Estacion nuevaEstacionDestino = new Estacion(listEstacionDestino.get(0), listEstacionDestino.get(1), listEstacionDestino.get(2), listEstacionDestino.get(3), listEstacionDestino.get(4));
				 
				List<Vertice> listaCaminos = new ArrayList<Vertice>();
				ArrayList<String> caminosMostrar = new ArrayList<String>();
				listaCaminos = grafo.masBarato(nuevaEstacionOrigen, nuevaEstacionDestino);
				
				model.setRowCount(0);
			
				String mostrar ="";
				for(int i=0; i<listaCaminos.size(); i++) {
						caminosMostrar.add(listaCaminos.get(i).getEst().getId()+" --> ");
				}
					for(int t=0; t<caminosMostrar.size();t++) {
						mostrar += caminosMostrar.get(t)+" ";
					}
					model.addRow(new Object [] {"Trayecto "+(1),mostrar});
					caminosMostrar.clear();
					mostrar = ""; 
				table.setModel(model);
				
				lblNewLabel_5.setVisible(true);
				lblNewLabel_6.setVisible(true);
				btnNewButton_1.setVisible(true);
				lblNewLabel_6.setText(grafo.calcularPrecioBoleto(listaCaminos).toString());
			}
		});
		botonCaminoMasBarato.setBounds(254, 256, 124, 23);
		getContentPane().add(botonCaminoMasBarato);
		
		botonCaminoMasCorto = new JRadioButton("M\u00E1s corto");
		botonCaminoMasCorto.setForeground(new Color(102, 0, 0));
		botonCaminoMasCorto.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonCaminoMasCorto.setBackground(new Color(255, 255, 204));
		botonCaminoMasCorto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Estacion nuevaEstacionOrigen = new Estacion(listEstacionOrigen.get(0), listEstacionOrigen.get(1), listEstacionOrigen.get(2), listEstacionOrigen.get(3), listEstacionOrigen.get(4));
				Estacion nuevaEstacionDestino = new Estacion(listEstacionDestino.get(0), listEstacionDestino.get(1), listEstacionDestino.get(2), listEstacionDestino.get(3), listEstacionDestino.get(4));
				 
				List<Vertice> listaCaminos = new ArrayList<Vertice>();
				ArrayList<String> caminosMostrar = new ArrayList<String>();
				listaCaminos = grafo.menorDistancia(nuevaEstacionOrigen, nuevaEstacionDestino);
				
				model.setRowCount(0);
			
				String mostrar ="";
				for(int i=0; i<listaCaminos.size(); i++) {
						caminosMostrar.add(listaCaminos.get(i).getEst().getId()+" --> ");
				}
					for(int t=0; t<caminosMostrar.size();t++) {
						mostrar += caminosMostrar.get(t)+" ";
					}
					model.addRow(new Object [] {"Trayecto "+(1),mostrar});
					caminosMostrar.clear();
					mostrar = ""; 
				table.setModel(model);
				
				lblNewLabel_5.setVisible(true);
				lblNewLabel_6.setVisible(true);
				btnNewButton_1.setVisible(true);
				lblNewLabel_6.setText(grafo.calcularPrecioBoleto(listaCaminos).toString());
			}
		});
		botonCaminoMasCorto.setBounds(434, 256, 124, 23);
		getContentPane().add(botonCaminoMasCorto);
		 
		bg.add(botonMasRapido);
		bg.add(botonCaminoMasBarato);
		bg.add(botonCaminoMasCorto);
		
		table = new JTable();
		table.setBounds(18, 109, 532, 56);
		
		js=new JScrollPane(table);
		js.setBounds(18, 120, 756, 96);
		js.setVisible(true);
		getContentPane().add(js);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				dispose();
			}
		});
		btnNewButton.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Iniciar compra");
		btnNewButton_1.setBackground(new Color(102, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 204));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				btnNewButton_3.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(589, 298, 185, 30);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
		
		JButton btnNewButton_2 = new JButton("Mostrar caminos");
		btnNewButton_2.setBackground(new Color(102, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 204));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.setRowCount(0);
				listEstacionOrigen.clear();
				listEstacionDestino.clear();
				grafo.armarGrafoEstacion(); 
				
				String origen = comboBoxEstacionOrigen.getSelectedItem().toString();
				String destino = comboBoxEstacionDestino.getSelectedItem().toString();
				
				ResultSet r = null;
				ResultSet r1 = null;
				EstacionesDAO estacionDao = new EstacionDAO_SQL();
					
				
				r = estacionDao.consultarDatos("estaciones","id", origen);
				r1 = estacionDao.consultarDatos("estaciones", "id", destino);
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

					List<List<Vertice>> listaCaminos = new ArrayList<List<Vertice>>();
					listaCaminos = grafo.caminos(nuevaEstacionOrigen, nuevaEstacionDestino);
						
						ArrayList<String> caminosMostrar = new ArrayList<String>();
							
						String mostrar ="";
						for(int i=0; i<listaCaminos.size(); i++) {
							for(int j=0; j<listaCaminos.get(i).size(); j++) {
								caminosMostrar.add(listaCaminos.get(i).get(j).getEst().getId()+" --> ");
							}
							for(int t=0; t<caminosMostrar.size();t++) {
								mostrar += caminosMostrar.get(t)+" ";
							}
							 
							model.addRow(new Object [] {"Trayecto "+(i+1),mostrar});
							caminosMostrar.clear();
							mostrar = "";
							} 
						table.setModel(model);
					 
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(589, 56, 185, 30);
		getContentPane().add(btnNewButton_2);
		
		lblNewLabel_5 = new JLabel("El precio del boleto para el camino seleccionado es:");
		lblNewLabel_5.setForeground(new Color(102, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(18, 298, 433, 30);
		getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		 panel = new JPanel();
		 panel.setBackground(new Color(102, 0, 0));
		panel.setBounds(18, 352, 756, 113);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JLabel lblNewLabel_7 = new JLabel("Datos del cliente:");
		lblNewLabel_7.setForeground(new Color(255, 255, 204));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(10, 11, 146, 23);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Nombre y apellido:");
		lblNewLabel_8.setForeground(new Color(255, 255, 204));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(93, 45, 146, 23);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Correo electr\u00F3nico:");
		lblNewLabel_9.setForeground(new Color(255, 255, 204));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_9.setBounds(93, 75, 146, 23);
		panel.add(lblNewLabel_9);
		
		IngresoTextoNombre = new JTextField();
		IngresoTextoNombre.setBounds(249, 44, 375, 20);
		panel.add(IngresoTextoNombre);
		IngresoTextoNombre.setColumns(10);
		
		ingresoTextoCorreo = new JTextField();
		ingresoTextoCorreo.setBounds(249, 78, 375, 20);
		panel.add(ingresoTextoCorreo);
		ingresoTextoCorreo.setColumns(10);
		
		btnNewButton_3 = new JButton("Comprar");
		btnNewButton_3.setBackground(new Color(102, 0, 0));
		btnNewButton_3.setForeground(new Color(255, 255, 204));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BoletoDAO bDAO = new BoletoDAO_SQL();
				
				int numeroBoleto = (int) ((Math.random()*10000)+1);
				String nombre = IngresoTextoNombre.getText();
				String correo = ingresoTextoCorreo.getText(); 
				LocalDateTime fecha = LocalDateTime.now();
				String estOrigen = comboBoxEstacionOrigen.getSelectedItem().toString();
				String estDestino = comboBoxEstacionDestino.getSelectedItem().toString();
				
				Double costo = Double.parseDouble(lblNewLabel_6.getText());
				
				String camino = "";
				
				if(botonMasRapido.isSelected()) {
					camino = (String) model.getValueAt(0, 1);
				}
				if(botonCaminoMasBarato.isSelected()) {
					camino = (String) model.getValueAt(0, 1);
				}
				if(botonCaminoMasCorto.isSelected()) {
					camino = (String) model.getValueAt(0, 1);
				}
				
				Boleto nuevoBoleto = new Boleto(numeroBoleto, nombre, correo, fecha, estOrigen, estDestino, camino, costo);
				 
				bDAO.registrarVenta(nuevoBoleto);
			
				limpiarFormulario();
			}
		});
		btnNewButton_3.setBounds(548, 470, 108, 30);
		getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Mostrar graficamente");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String origen = comboBoxEstacionOrigen.getSelectedItem().toString();
				String destino = comboBoxEstacionDestino.getSelectedItem().toString();
				//para pantalla grafica
				PantallaGrafica pantallaGrafo = new PantallaGrafica(origen, destino);
				pantallaGrafo.setTitle("Pantalla grafica de Grafos");
				pantallaGrafo.setSize(800, 550); 
				pantallaGrafo.setLocationRelativeTo(null);
				pantallaGrafo.setModal(true);
				pantallaGrafo.setVisible(true);
					
			}
		});
		btnNewButton_4.setForeground(new Color(255, 255, 204));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_4.setBackground(new Color(102, 0, 0));
		btnNewButton_4.setBounds(529, 223, 245, 30);
		getContentPane().add(btnNewButton_4);
		btnNewButton_3.setVisible(false);
		
		try {
			Connection cn = ConnectionPostgres.conectar();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM estaciones ORDER BY id");
			while(rs.next()) {
				this.comboBoxEstacionOrigen.addItem(rs.getString("id"));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		try {
			Connection cn = ConnectionPostgres.conectar();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM estaciones ORDER BY id");
			while(rs.next()) {
				this.comboBoxEstacionDestino.addItem(rs.getString("id"));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		
	}
	
	public void limpiarFormulario() {
		comboBoxEstacionDestino.setSelectedIndex(0);
		comboBoxEstacionOrigen.setSelectedIndex(0);
		bg.clearSelection();
		panel.setVisible(false); 
		IngresoTextoNombre.setText(""); 
		ingresoTextoCorreo.setText("");
		model.setRowCount(0);
		lblNewLabel_6.setText("");
		lblNewLabel_5.setVisible(false);
		btnNewButton_1.setVisible(false);
		listEstacionOrigen.clear();
		listEstacionDestino.clear();
	}
	
}
