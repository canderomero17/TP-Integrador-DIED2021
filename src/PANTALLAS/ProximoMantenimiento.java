package PANTALLAS;
 
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import EstructuraGRAFO.Grafo;
import TP_DAO.EstacionDAO_SQL;
import TP_DAO.EstacionesDAO; 

@SuppressWarnings("serial")
public class ProximoMantenimiento extends JDialog {
	private JTable table;
	private JScrollPane js;
	private JButton btnConsultar;
	private DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProximoMantenimiento dialog = new ProximoMantenimiento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProximoMantenimiento() {
		getContentPane().setBackground(new Color(255, 255, 204));
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		
		Grafo grafo = new Grafo(); 
		grafo.armarGrafoEstacion();
		
		JLabel lblNewLabel = new JLabel("Pr\u00F3ximo mantenimiento");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 600, 45);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Estaciones ordenadas seg\u00FAn el pr\u00F3ximo mantenimiento que les toca:");
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(18, 96, 610, 23);
		getContentPane().add(lblNewLabel_1);
		
		table = new JTable();
		table.setBounds(18, 109, 532, 56);
		
		js=new JScrollPane(table);
		js.setBounds(18, 140, 750, 230);
		js.setVisible(true);
		getContentPane().add(js);
		
		btnConsultar = new JButton("Consultar ");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstacionesDAO estacionDAO = new EstacionDAO_SQL();
				ResultSet result = estacionDAO.compararFechas();
		
				model = new DefaultTableModel();
		
				model.setColumnIdentifiers(new Object[] {"ID estación", "Fecha inicio mantenimiento", "Fecha fin mantenimiento"});
		
				String sinDato = "";
				String sinDatoFin = "";
				
				try {
					while(result.next()) {
						if(result.getString("fechainicio")==null) {
							sinDato = "Sin fecha de mantenimiento";
							sinDatoFin = "Sin fecha final de mantenimiento";
							model.addRow(new Object [] {result.getString("id"),sinDato,sinDatoFin});
						}
						else if(result.getString("fechafin")==null) {
							sinDato = "Sin fecha final de mantenimiento";
							model.addRow(new Object [] {result.getString("id"),result.getString("fechainicio"),sinDato});
						}else {
							model.addRow(new Object [] {result.getString("id"),result.getString("fechainicio"),result.getString("fechafin")});
						}
					}
					table.setModel(model);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnConsultar.setForeground(new Color(255, 255, 204));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConsultar.setBackground(new Color(102, 0, 0));
		btnConsultar.setBounds(638, 92, 130, 30);
		getContentPane().add(btnConsultar);
	
		
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
		btnNewButton.setBounds(660, 470, 108, 30);
		getContentPane().add(btnNewButton);
	}
	
	public void limpiarFormulario() {
		model.setRowCount(0);
	}

}
