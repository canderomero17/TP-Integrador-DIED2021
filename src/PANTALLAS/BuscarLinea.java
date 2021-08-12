package PANTALLAS;
 
import java.awt.Color; 
import javax.swing.JButton;
import javax.swing.JDialog; 
import javax.swing.JScrollPane; 
import javax.swing.table.DefaultTableModel;
import TP_DAO.LineasDAO;
import TP_DAO.LineasDAO_SQL;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class BuscarLinea extends JDialog {

	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private int tamAlto = 550;
	private int tamAncho = 800;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	@SuppressWarnings("unused")
	private JCheckBox chckbxNewCheckBox;
	@SuppressWarnings("unused")
	private JCheckBox chckbxNewCheckBox_1;
	@SuppressWarnings("unused")
	private JCheckBox chckbxNewCheckBox_2;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	ButtonGroup bg = new ButtonGroup();
	private DefaultTableModel model = new DefaultTableModel();

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BuscarLinea() {
		
		getContentPane().setBackground(new Color(255, 255, 204));
		getContentPane().setForeground(new Color(255, 255, 204));
		setBounds(100, 100, tamAncho, tamAlto);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar l\u00EDnea");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setBackground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 180, 43);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setBackground(new Color(102, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(56, 90, 116, 24);
		getContentPane().add(lblNewLabel_1);
		
		
		lblNewLabel_2 = new JLabel("Ingrese COLOR:");
		lblNewLabel_2.setForeground(new Color(102, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(56, 153, 148, 24);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		lblNewLabel_3 = new JLabel("Ingrese NOMBRE:");
		lblNewLabel_3.setForeground(new Color(102, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(56, 209, 180, 18);
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		lblNewLabel_4 = new JLabel("Seleccione ESTADO:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setForeground(new Color(102, 0, 0));
		lblNewLabel_4.setBounds(56, 265, 180, 14);
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(251, 157, 238, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(251, 211, 238, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Activa", "No activa"}));
		comboBox.setBounds(251, 262, 238, 24);
		getContentPane().add(comboBox);
		comboBox.setVisible(false);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				}
			));
		table.setBounds(35, 271, 414, 98);
		table.setVisible(false);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(56, 312, 691, 122);
		js.setVisible(true);
		getContentPane().add(js);
        
        
		JCheckBox chckbxNewCheckBox = new JCheckBox("COLOR");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxNewCheckBox.setBackground(new Color(255, 255, 204));
		chckbxNewCheckBox.setForeground(new Color(102, 0, 0));
		bg.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(true);
				textField.setVisible(true);
				table.setVisible(true);
				
				lblNewLabel_3.setVisible(false);
				textField_1.setVisible(false);
				lblNewLabel_4.setVisible(false);
				comboBox.setVisible(false);
				btnNewButton.setVisible(true);
			}
		});
		
		chckbxNewCheckBox.setBounds(201, 91, 97, 23);
		getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("NOMBRE");
		bg.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxNewCheckBox_1.setForeground(new Color(102, 0, 0));
		chckbxNewCheckBox_1.setBackground(new Color(255, 255, 204));
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(false);
				textField.setVisible(false);
				
				lblNewLabel_3.setVisible(true);
				textField_1.setVisible(true);
				table.setVisible(true);
				
				lblNewLabel_4.setVisible(false);
				comboBox.setVisible(false);
				btnNewButton.setVisible(true);
			}
		});
		chckbxNewCheckBox_1.setBounds(301, 91, 97, 23);
		getContentPane().add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("ESTADO");
		bg.add(chckbxNewCheckBox_2);
		chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxNewCheckBox_2.setBackground(new Color(255, 255, 204));
		chckbxNewCheckBox_2.setForeground(new Color(102, 0, 0));
		chckbxNewCheckBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(false);
				textField.setVisible(false);
				lblNewLabel_3.setVisible(false);
				textField_1.setVisible(false);
				
				lblNewLabel_4.setVisible(true);
				comboBox.setVisible(true);
				table.setVisible(true);
				btnNewButton.setVisible(true);
			}
		});
		chckbxNewCheckBox_2.setBounds(415, 91, 97, 23);
		getContentPane().add(chckbxNewCheckBox_2);
		
		btnNewButton = new JButton("Consultar");
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet result = null;
                LineasDAO lDAO = new LineasDAO_SQL();
                model = new DefaultTableModel() {
                	public boolean isCellEditable(int filas, int columnas) {
                		if(columnas<0 && filas<0) {
                			return true;
						} else {
							return false;
						}
                	};
                };
                model.setColumnIdentifiers(new Object[] {"Color","Nombre", "Estado"});
  
                	if(chckbxNewCheckBox.isSelected()) {
                		String color = textField.getText();
                		result = lDAO.consultarDatos("color", color);
                	}
                		
                    if(chckbxNewCheckBox_1.isSelected()) { //si se seleccionó nombre
                    	String nombre = textField_1.getText();
                    	result = lDAO.consultarDatos("nombre", nombre);
                    }
                    
                    if(chckbxNewCheckBox_2.isSelected()) { //si se seleccionó estado
                        String estado = comboBox.getSelectedItem().toString();
                        result = lDAO.consultarDatos("estado", estado);
                        }
                    
                    try {
                        while(result.next()) {
                            model.addRow(new Object [] {result.getString("color"), result.getString("nombre"), result.getString("estado")});
                        }
                        table.setModel(model);

                    }catch(Exception exc){
                        exc.printStackTrace();
                    }
                    
                    limpiarFormulario();
                    
                    }
           
		});
		btnNewButton.setBounds(618, 265, 129, 24);
		getContentPane().add(btnNewButton);
		btnNewButton.setVisible(false);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setForeground(new Color(255, 255, 204));
		btnNewButton_1.setBackground(new Color(102, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(model.getRowCount() == 0) {
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
		
		
	}
	
	public void limpiarFormulario() {
		textField.setText("");
		textField_1.setText("");
	 	comboBox.setSelectedIndex(0);
		bg.clearSelection();
		textField.setVisible(false);
		textField_1.setVisible(false);
		comboBox.setVisible(false);
 		lblNewLabel_2.setVisible(false);
 		lblNewLabel_3.setVisible(false);
 		lblNewLabel_4.setVisible(false);
 		btnNewButton.setVisible(false);
	}
	
	public void limpiarFormulario1() {
		textField.setText("");
		textField_1.setText("");
	 	comboBox.setSelectedIndex(0);
		bg.clearSelection();
		textField.setVisible(false);
		textField_1.setVisible(false);
		comboBox.setVisible(false);
 		lblNewLabel_2.setVisible(false);
 		lblNewLabel_3.setVisible(false);
 		lblNewLabel_4.setVisible(false);
 		btnNewButton.setVisible(false);
 		model.setRowCount(0);
	}

}
