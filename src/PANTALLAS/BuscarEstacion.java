package PANTALLAS;
  
import javax.swing.JButton;
import javax.swing.JDialog; 
import javax.swing.JScrollPane; 
import javax.swing.table.DefaultTableModel; 
import TP_DAO.EstacionDAO_SQL;
import TP_DAO.EstacionesDAO; 
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;  
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

@SuppressWarnings("serial")
public class BuscarEstacion extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_2;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_3;
	ButtonGroup bg = new ButtonGroup();
	private DefaultTableModel model;
	private JButton btnNewButton;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BuscarEstacion() {
		getContentPane().setBackground(new Color(255, 255, 204));
		
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Buscar Estaci\u00F3n");
			lblNewLabel.setForeground(new Color(102, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel.setBounds(10, 11, 600, 23);
			getContentPane().add(lblNewLabel);
		}
		
		
		JLabel lbl_filtrarPor = new JLabel("Filtrar por:");
		lbl_filtrarPor.setBackground(new Color(255, 255, 204));
		lbl_filtrarPor.setForeground(new Color(102, 0, 0));
		lbl_filtrarPor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_filtrarPor.setBounds(312, 31, 117, 23);
		getContentPane().add(lbl_filtrarPor);
		
		lblNewLabel_1 = new JLabel("Ingrese ID:");
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(240, 112, 272, 17);
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		lblNewLabel_2 = new JLabel("Ingrese NOMBRE:");
		lblNewLabel_2.setForeground(new Color(102, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(240, 142, 272, 17);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		lblNewLabel_3 = new JLabel("Seleccione HORARIO APERTURA:");
		lblNewLabel_3.setForeground(new Color(102, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(240, 176, 272, 17);
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		lblNewLabel_4 = new JLabel("Seleccione HORARIO CIERRE:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setForeground(new Color(102, 0, 0));
		lblNewLabel_4.setBounds(240, 204, 272, 17);
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		lblNewLabel_5 = new JLabel("Seleccione ESTADO:");
		lblNewLabel_5.setForeground(new Color(102, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(240, 236, 272, 17);
		getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(522, 109, 142, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(522, 139, 142, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setForeground(new Color(255, 255, 204));
		comboBox_3.setBackground(new Color(102, 0, 0));
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"04:00","04:30","05:00","05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30","09:00", "09:30", "10:00"}));
		comboBox_3.setBounds(522, 169, 142, 24);
		getContentPane().add(comboBox_3);
		comboBox_3.setVisible(false);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setForeground(new Color(255, 255, 204));
		comboBox_1.setBackground(new Color(102, 0, 0));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"16:00","16:30","17:00","17:30","18:00","18:30", "19:00","19:30", "20:00","20:30","21:00","21:30","22:00"}));
		comboBox_1.setBounds(522, 199, 142, 24);
		getContentPane().add(comboBox_1);
		comboBox_1.setVisible(false);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBackground(new Color(102, 0, 0));
		comboBox_2.setForeground(new Color(255, 255, 204));
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Operativa", "En mantenimiento"}));
		comboBox_2.setBounds(522, 229, 142, 24);
		getContentPane().add(comboBox_2);
		comboBox_2.setVisible(false);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table.setBounds(35, 271, 414, 98);
		getContentPane().add(table);
		table.setVisible(false);
		JScrollPane js=new JScrollPane(table);
        js.setBounds(111, 329, 553, 112);
        js.setVisible(true);
        getContentPane().add(js);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("NOMBRE");
		chckbxNewCheckBox.setBackground(new Color(255, 255, 204));
		chckbxNewCheckBox.setForeground(new Color(102, 0, 0));
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		bg.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(true);
				textField_1.setVisible(true);
				table.setVisible(true);
				lblNewLabel_1.setVisible(false);
				textField.setVisible(false);
				lblNewLabel_3.setVisible(false);
				comboBox_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
				comboBox_1.setVisible(false);
				lblNewLabel_5.setVisible(false);
				comboBox_2.setVisible(false);
				btnNewButton.setVisible(true);
			}
		});
		chckbxNewCheckBox.setBounds(173, 61, 83, 23);
		getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("HORARIO APERTURA");
		chckbxNewCheckBox_1.setBackground(new Color(255, 255, 204));
		chckbxNewCheckBox_1.setForeground(new Color(102, 0, 0));
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		bg.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(false);
				textField_1.setVisible(false);
				table.setVisible(true);
				lblNewLabel_1.setVisible(false);
				textField.setVisible(false);
				lblNewLabel_3.setVisible(true);
				comboBox_3.setVisible(true);
				lblNewLabel_4.setVisible(false);
				comboBox_1.setVisible(false);
				lblNewLabel_5.setVisible(false);
				comboBox_2.setVisible(false);
				btnNewButton.setVisible(true);
			}
		});
		chckbxNewCheckBox_1.setBounds(258, 61, 165, 23);
		getContentPane().add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("HORARIO CIERRE");
		chckbxNewCheckBox_2.setBackground(new Color(255, 255, 204));
		chckbxNewCheckBox_2.setForeground(new Color(102, 0, 0));
		chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		bg.add(chckbxNewCheckBox_2);
		chckbxNewCheckBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(false);
				textField_1.setVisible(false);
				table.setVisible(true);
				lblNewLabel_1.setVisible(false);
				textField.setVisible(false);
				lblNewLabel_3.setVisible(false);
				comboBox_3.setVisible(false);
				lblNewLabel_4.setVisible(true);
				comboBox_1.setVisible(true);
				lblNewLabel_5.setVisible(false);
				comboBox_2.setVisible(false);
				btnNewButton.setVisible(true);
			}
		});
		chckbxNewCheckBox_2.setBounds(425, 62, 142, 23);
		getContentPane().add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("ESTADO");
		chckbxNewCheckBox_3.setBackground(new Color(255, 255, 204));
		chckbxNewCheckBox_3.setForeground(new Color(102, 0, 0));
		chckbxNewCheckBox_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		bg.add(chckbxNewCheckBox_3);
		chckbxNewCheckBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(false);
				textField_1.setVisible(false);
				table.setVisible(true);
				lblNewLabel_1.setVisible(false);
				textField.setVisible(false);
				lblNewLabel_3.setVisible(false);
				comboBox_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
				comboBox_1.setVisible(false);
				lblNewLabel_5.setVisible(true);
				comboBox_2.setVisible(true);
				btnNewButton.setVisible(true);
			}
		});
		chckbxNewCheckBox_3.setBounds(569, 61, 95, 23);
		getContentPane().add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("ID");
		chckbxNewCheckBox_4.setBackground(new Color(255, 255, 204));
		chckbxNewCheckBox_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxNewCheckBox_4.setForeground(new Color(102, 0, 0));
		bg.add(chckbxNewCheckBox_4);
		chckbxNewCheckBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(false);
				textField_1.setVisible(false);
				table.setVisible(true);
				lblNewLabel_1.setVisible(true);
				textField.setVisible(true);
				lblNewLabel_3.setVisible(false);
				comboBox_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
				comboBox_1.setVisible(false);
				lblNewLabel_5.setVisible(false);
				comboBox_2.setVisible(false);
				
				btnNewButton.setVisible(true);
			}
		});
		chckbxNewCheckBox_4.setBounds(111, 61, 60, 23);
		getContentPane().add(chckbxNewCheckBox_4);
		
		btnNewButton = new JButton("Consultar");
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16)); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet result = null;
                EstacionesDAO eDAO = new EstacionDAO_SQL();
                model = new DefaultTableModel() {
                public boolean isCellEditable(int filas, int columnas) {
					if(columnas<0 && filas<0) {
						return true;
					} else {
						return false;
					}
				};
                };
                model.setColumnIdentifiers(new Object[] {"ID","Nombre","Horario de Apertura", "Horario de cierre", "Estado"});

                	if(chckbxNewCheckBox_4.isSelected()) { //si se seleccionó id
                		String id = textField.getText();
                		result = eDAO.consultarDatos("estaciones","id", id);
                	}
                
                    if(chckbxNewCheckBox.isSelected()) { //si se seleccionó nombre
                    	String nombre = textField_1.getText();
                    	result = eDAO.consultarDatos("estaciones","nombre", nombre);
                    }
                    
                    if(chckbxNewCheckBox_1.isSelected()) { //si se seleccionó horario apertura
                        String horarioape = comboBox_3.getSelectedItem().toString();
                        result = eDAO.consultarDatos("estaciones","horarioap", horarioape);
                        }
                    
                    if(chckbxNewCheckBox_2.isSelected()) { //si se seleccionó horario cierre
                        String horariocierre = comboBox_1.getSelectedItem().toString();
                        result = eDAO.consultarDatos("estaciones","horariocierre", horariocierre);
                        }
                    
                    if(chckbxNewCheckBox_3.isSelected()) { //si se seleccionó selecciono estado
                        String estado = comboBox_2.getSelectedItem().toString();
                        result = eDAO.consultarDatos("estaciones","estado", estado);
                        }
                    
                    try {
                        while(result.next()) {
                            model.addRow(new Object [] {result.getString("id"), result.getString("nombre"), result.getString("horarioap"),result.getString("horariocierre"), result.getString("estado")});
                        }
                        table.setModel(model);

                    }catch(Exception exc){
                        exc.printStackTrace();
                    }
                    
                    limpiarFormulario();
                    
                 }	
		});   
		btnNewButton.setBounds(522, 288, 142, 30);
		getContentPane().add(btnNewButton);
		btnNewButton.setVisible(false);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBackground(new Color(102, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 204));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(model.getRowCount()==0) {
					limpiarFormulario();
				}else {
					limpiarFormulario1();
				}
				
				dispose();
			}
		});
		btnNewButton_1.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton_1);
		
	}
	
	public void limpiarFormulario() {
		textField.setText("");
		textField_1.setText("");
		comboBox_1.setVisible(false);
		comboBox_2.setVisible(false);
		comboBox_3.setVisible(false);
		comboBox_1.setSelectedIndex(0);
		comboBox_2.setSelectedIndex(0);
		comboBox_3.setSelectedIndex(0);
		bg.clearSelection();
		textField.setVisible(false);
		textField_1.setVisible(false);
		lblNewLabel_1.setVisible(false);
		lblNewLabel_2.setVisible(false);
		lblNewLabel_4.setVisible(false);
		lblNewLabel_3.setVisible(false);
		lblNewLabel_5.setVisible(false);
		btnNewButton.setVisible(false);
	}
	
	public void limpiarFormulario1() {
		textField.setText("");
		textField_1.setText("");
		comboBox_1.setVisible(false);
		comboBox_2.setVisible(false);
		comboBox_3.setVisible(false);
		comboBox_1.setSelectedIndex(0);
		comboBox_2.setSelectedIndex(0);
		comboBox_3.setSelectedIndex(0);
		bg.clearSelection();
		textField.setVisible(false);
		textField_1.setVisible(false);
		lblNewLabel_1.setVisible(false);
		lblNewLabel_2.setVisible(false);
		lblNewLabel_4.setVisible(false);
		lblNewLabel_3.setVisible(false);
		lblNewLabel_5.setVisible(false);
		btnNewButton.setVisible(false);
		model.setRowCount(0);
	}
}
