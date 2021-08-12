package PANTALLAS;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel; 
import TP_DAO.LineasDAO;
import TP_DAO.LineasDAO_SQL; 
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class EliminarLinea extends JDialog {

	private JTextField textField;
	private JTable table;
	private int tamAlto = 550;
	private int tamAncho = 800;
	private DefaultTableModel modelo = new DefaultTableModel();
 
	/**
	 * Create the dialog.
	 */
	public EliminarLinea() {
		getContentPane().setBackground(new Color(255, 255, 204));
		getContentPane().setForeground(new Color(255, 255, 204));
		setBounds(100, 100, tamAncho, tamAlto);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Eliminar l\u00EDnea de transporte");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(26, 10, 383, 50);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Inserte el color de la linea que desea eliminar:");
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(61, 116, 402, 21);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(495, 117, 142, 30);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBackground(new Color(102, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 204));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(modelo.getRowCount() == 0) {
					limpiarFormulario();
				} else {
					limpiarFormulario1();
				} 
				 
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.setBackground(new Color(102, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 204));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LineasDAO ldao = new LineasDAO_SQL();
				String color = textField.getText();
				ldao.EliminarLineas(color);	
				
				limpiarFormulario1();
			}
		});
				
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(548, 470, 108, 30);
		getContentPane().add(btnNewButton_2);
		
		table = new JTable();
		table.setBounds(61, 232, 587, 164);
		
		JScrollPane js =new JScrollPane(table);
		js.setBounds(61, 232, 587, 164);
		getContentPane().add(js);
		js.setVisible(true);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LineasDAO ldao = new LineasDAO_SQL();
				
				modelo = new DefaultTableModel() {
					public boolean isCellEditable(int filas, int columnas) {
						if(columnas==1 && filas==1) {
							return true;
						} else {
							return false;
						}
					};
				};
					String color = textField.getText();
					ResultSet rs = ldao.getTabla("SELECT * FROM lineasdetransporte WHERE color = '"+color+"'");
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(495, 173, 142, 30);
		getContentPane().add(btnNewButton);
	
	}
	
		public void limpiarFormulario1() {
		textField.setText("");
		modelo.setRowCount(0);
		}
		
		public void limpiarFormulario() {
			textField.setText("");
		}
}