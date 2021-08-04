package PANTALLAS;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import TP_DAO.EstacionDAO_SQL;
import TP_DAO.EstacionesDAO;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;

@SuppressWarnings("serial")
public class EditarEstacion extends JDialog {
	private JTextField textField;
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel(); 
	
	/**
	 * Create the panel.
	 */
	public EditarEstacion() {
		getContentPane().setBackground(new Color(255, 255, 204));
		getContentPane().setLayout(null);
		setBounds(100, 100, 800, 550);
		
		JLabel lblNewLabel = new JLabel("Editar Estaci\u00F3n");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 600, 45);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Inserte el ID de la estaci\u00F3n que desea editar:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setBounds(116, 117, 369, 30);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(495, 117, 142, 30);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EstacionesDAO eDAO = new EstacionDAO_SQL();
				  
				String id = textField.getText();
				
				modelo = new DefaultTableModel() {
					public boolean isCellEditable(int fila, int columna) { //no se puede editar el estado
						if(fila<1 && columna<4) {
							return true;
						}else return false;
					};
				};
				
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
		
		table = new JTable();
		table.setBounds(48, 98, 333, 78);
		getContentPane().add(table);
		JScrollPane js=new JScrollPane(table);
        js.setBounds(147, 236, 490, 171);
        js.setVisible(true);
        getContentPane().add(js);
		
		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setBackground(new Color(102, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 204));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EstacionDAO_SQL eDAO = new EstacionDAO_SQL();
				 
				eDAO.editarEstacion(table);
				
				limpiarFormulario();
			}
		});
		btnNewButton_1.setBounds(548, 470, 108, 30);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setBackground(new Color(102, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 204));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				dispose();
			}
		});
		btnNewButton_2.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton_2);

	}
	
	public void limpiarFormulario() {
		textField.setText("");
		modelo.setRowCount(0);
	}
	

}
