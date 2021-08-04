package PANTALLAS;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet; 
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import TP_DAO.LineasDAO;
import TP_DAO.LineasDAO_SQL;

import java.awt.Color;

@SuppressWarnings("serial")
public class EditarLinea extends JDialog {

	private JTextField textField;
	private JTable table;
	private int tamAlto = 550;
	private int tamAncho = 800;
	private DefaultTableModel modelo = new DefaultTableModel(); 
	private JScrollPane js;
 

	/**
	 * Create the dialog.
	 */
	public EditarLinea() {
		getContentPane().setBackground(new Color(255, 255, 204));
		setBounds(100, 100, tamAncho, tamAlto);
		getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Editar L\u00EDnea de Transporte");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setBackground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 373, 45);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Inserte el color de la l\u00EDnea que desea editar:");
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(61, 116, 402, 21);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(495, 117, 142, 30);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LineasDAO lDAO = new LineasDAO_SQL();
				
				DefaultTableModel modelo = new DefaultTableModel();
				
				String color = textField.getText();
				ResultSet rs = lDAO.getTabla("SELECT * FROM lineasdetransporte WHERE color = '"+color+"'");
				modelo.setColumnIdentifiers(new Object[] {"Color", "Nombre", "Estado"});
				
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
		btnNewButton.setBounds(495, 173, 142, 30);
		getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setBounds(55, 236, 617, 147);
		
		js = new JScrollPane(table);
		js.setBounds(61, 232, 576, 164);
		getContentPane().add(js);
		js.setVisible(true);
		
		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBackground(new Color(102, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 204));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LineasDAO lDAO = new LineasDAO_SQL();
				 
				lDAO.editarLineas(table);
				
				limpiarFormulario();
					
			}
		});
		btnNewButton_1.setBounds(548, 470, 108, 30);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBackground(new Color(102, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 204));
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