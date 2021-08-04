package PANTALLAS;

import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Objetos.Estacion;
import TP_DAO.EstacionDAO_SQL;
import TP_DAO.EstacionesDAO;

import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class AgregarEstacion extends JDialog {
	
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private ButtonGroup grupo = new ButtonGroup();
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AgregarEstacion() {
		getContentPane().setBackground(new Color(255, 255, 204));
	    getContentPane().setLayout(null);
	    setBackground(new Color(255, 255, 204)); 
		 
		setBounds(100, 100, 800, 550);
		
		lblNewLabel = new JLabel("Id:");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(114, 123, 178, 20);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(302, 125, 101, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(114, 181, 178, 23);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(302, 184, 101, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Horario de apertura:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setForeground(new Color(102, 0, 0));
		lblNewLabel_2.setBounds(114, 242, 178, 22);
		getContentPane().add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setBackground(new Color(102, 0, 0));
		comboBox.setForeground(new Color(255, 255, 204));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"04:00","04:30","05:00","05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30","09:00", "09:30", "10:00"}));
		comboBox.setBounds(302, 243, 101, 24);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Horario de cierre:");
		lblNewLabel_3.setForeground(new Color(102, 0, 0));
		lblNewLabel_3.setBackground(new Color(255, 255, 204));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(114, 303, 178, 19);
		getContentPane().add(lblNewLabel_3);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setForeground(new Color(255, 255, 204));
		comboBox_1.setBackground(new Color(102, 0, 0));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"16:00","16:30","17:00","17:30","18:00","18:30", "19:00","19:30", "20:00","20:30","21:00","21:30","22:00"}));
		comboBox_1.setBounds(302, 302, 101, 24);
		getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("Estado:");
		lblNewLabel_4.setForeground(new Color(102, 0, 0));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(114, 350, 95, 24);
		getContentPane().add(lblNewLabel_4);
		
		JRadioButton estado = new JRadioButton("Operativa");
		estado.setForeground(new Color(102, 0, 0));
		estado.setFont(new Font("Tahoma", Font.BOLD, 14));
		estado.setBackground(new Color(255, 255, 204));
		estado.setBounds(302, 351, 136, 23);
		getContentPane().add(estado);
		
		JRadioButton estado1 = new JRadioButton("En mantenimiento");
		estado1.setBackground(new Color(255, 255, 204));
		estado1.setForeground(new Color(102, 0, 0));
		estado1.setFont(new Font("Tahoma", Font.BOLD, 14));
		estado1.setBounds(440, 351, 178, 23);
		getContentPane().add(estado1);
		
		grupo.add(estado);
		grupo.add(estado1);
		
		JButton btnNewButton_1 = new JButton("Guardar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBackground(new Color(102, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 204));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EstacionesDAO eDAO = new EstacionDAO_SQL();
				
			//	ConnectionPostgres.conectar();
				
				String id =  textField.getText();
				String nombre = textField_1.getText();
				String horaAper = comboBox.getSelectedItem().toString();
				String horaFinal = comboBox_1.getSelectedItem().toString();
				
				String posibleEstado;
				if(estado.isSelected()) posibleEstado = "Operativa";
				else posibleEstado = "En mantenimiento"; 
				
				Estacion estacionNueva = new Estacion(id, nombre, horaAper, horaFinal, posibleEstado);
				
				eDAO.guardarEstacion(estacionNueva);
				
				limpiarFormulario();
				
			}
		});
		btnNewButton_1.setBounds(548, 470, 108, 30);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setForeground(new Color(255, 255, 204));
		btnNewButton_2.setBackground(new Color(102, 0, 0));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				dispose();
			}
		});
		btnNewButton_2.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("Agregar Estaci\u00F3n");
		lblNewLabel_5.setForeground(new Color(102, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_5.setBounds(10, 11, 600, 45);
		getContentPane().add(lblNewLabel_5);

	}
	
	public void limpiarFormulario() {
		textField.setText("");
		textField_1.setText("");
		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		grupo.clearSelection();
	}

}
