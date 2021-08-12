package PANTALLAS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import TP_DAO.EstacionDAO_SQL;
import TP_DAO.EstacionesDAO;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class EliminarEstacion extends JDialog {
	private JTextField textField_2;
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel();
	/**
	 * Create the panel.
	 */
	public EliminarEstacion() {
		getContentPane().setBackground(new Color(255, 255, 204));
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 550);
		
		JLabel lblNewLabel_5 = new JLabel("Inserte el ID de la estaci\u00F3n que desea eliminar:");
		lblNewLabel_5.setForeground(new Color(102, 0, 0));
		lblNewLabel_5.setBackground(new Color(255, 255, 204));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(116, 117, 369, 30);
		getContentPane().add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(495, 117, 142, 30);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Eliminar");
		btnNewButton_4.setForeground(new Color(255, 255, 204));
		btnNewButton_4.setBackground(new Color(102, 0, 0));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstacionesDAO eDAO = new EstacionDAO_SQL();
				//ConnectionPostgres.conectar();
				
				String id =  textField_2.getText();
				
				eDAO.eliminarEstacion(id);
				
				limpiarFormulario1();
			}
		});
		btnNewButton_4.setBounds(548, 470, 108, 30);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cancelar");
		btnNewButton_5.setForeground(new Color(255, 255, 204));
		btnNewButton_5.setBackground(new Color(102, 0, 0));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		 		if(modelo.getRowCount() == 0) {
					limpiarFormulario();
				} else {
					limpiarFormulario1();
				} 
				 
				dispose();
			}
		});
		btnNewButton_5.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton_5);
		
		//creo una tala para mostrar las estaciones
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBounds(48, 98, 333, 78);
		getContentPane().add(table);
		JScrollPane js=new JScrollPane(table);
        js.setBounds(116, 236, 521, 171);
        js.setVisible(true);
        getContentPane().add(js);
	
		
		JLabel lblNewLabel = new JLabel("Eliminar Estaci\u00F3n");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 600, 45);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EstacionesDAO eDAO = new EstacionDAO_SQL();
				
				 modelo = new DefaultTableModel() {
					public boolean isCellEditable(int filas, int columnas) {
						if(columnas<0 && filas<0) {
							return true;
						} else {
							return false;
						}
					};
				};
				
				String id = textField_2.getText();
				ResultSet rs = eDAO.getTabla("SELECT * FROM estaciones WHERE id = '"+id+"'");
				modelo.setColumnIdentifiers(new Object[] {"ID", "Nombre", "Horario Apertura", "Horario Cierre", "Estado"});
				
				try {
					while(rs.next()) {
						modelo.addRow(new Object[] {rs.getString("id"), rs.getString("nombre"), rs.getString("horarioap"), rs.getString("horariocierre"), rs.getString("estado")});
					}
					table.setModel(modelo);
					
				} catch(Exception ex) {
					System.out.println(ex);
				}
			}
		});
		btnNewButton.setBounds(495, 173, 142, 30);
		getContentPane().add(btnNewButton);
		
	}
	
	public void limpiarFormulario() {
		textField_2.setText("");
	}
	public void limpiarFormulario1() {
		textField_2.setText("");
		modelo.setRowCount(0);
	}
	
}
