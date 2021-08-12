package PANTALLAS;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Objetos.Linea;
import TP_DAO.LineasDAO;
import TP_DAO.LineasDAO_SQL; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

@SuppressWarnings("serial")
public class AgregarLineaTransporte extends JDialog {

	private JTextField textField;
	private JTextField textField_1;
	private int tamAlto = 550;
	private int tamAncho = 800;
	private ButtonGroup bg = new ButtonGroup();

	 
	/**
	 * Create the dialog.
	 */
	public AgregarLineaTransporte() {
			getContentPane().setBackground(new Color(255, 255, 204));
			getContentPane().setForeground(new Color(255, 255, 204));
			setBounds(100, 100, tamAncho, tamAlto);
			getContentPane().setLayout(null);
		
			JLabel lblNewLabel = new JLabel("Agregar L\u00EDnea de Transporte");
			lblNewLabel.setForeground(new Color(102, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel.setBounds(10, 11, 600, 45);
			getContentPane().add(lblNewLabel);
		
		
			JLabel lblNewLabel_1 = new JLabel("Color:");
			lblNewLabel_1.setForeground(new Color(102, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_1.setBounds(176, 111, 67, 25);
			getContentPane().add(lblNewLabel_1);
		
		
			JLabel lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setForeground(new Color(102, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_2.setBounds(176, 176, 87, 25);
			getContentPane().add(lblNewLabel_2);
		
		
			JLabel lblNewLabel_3 = new JLabel("Estado:");
			lblNewLabel_3.setForeground(new Color(102, 0, 0));
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_3.setBounds(176, 239, 87, 30);
			getContentPane().add(lblNewLabel_3);
		
		
			JRadioButton estado = new JRadioButton("Activa");
			estado.setFont(new Font("Tahoma", Font.BOLD, 14));
			estado.setBackground(new Color(255, 255, 204));
			estado.setForeground(new Color(102, 0, 0));
			estado.setBounds(304, 243, 87, 23);
			getContentPane().add(estado);
		
		JRadioButton estado1 = new JRadioButton("No activa");
		estado1.setFont(new Font("Tahoma", Font.BOLD, 14));
		estado1.setBackground(new Color(255, 255, 204));
		estado1.setForeground(new Color(102, 0, 0));
		estado1.setBounds(393, 243, 108, 23);
		getContentPane().add(estado1);
		
		bg.add(estado);
		bg.add(estado1);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineasDAO lDAO = new LineasDAO_SQL();
				
				String color = textField.getText();
				String nombre = textField_1.getText();
				
				String posibleestado;
				if(estado.isSelected()) posibleestado = "Activa";
				else posibleestado = "No activa"; 
				
				Linea lineaNueva = new Linea(color, nombre, posibleestado);
				
				lDAO.guardarLinea(lineaNueva);
				
				limpiarFormulario();
			}
			
		});
		btnNewButton.setBounds(548, 470, 108, 30);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBackground(new Color(102, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 204));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				dispose();
			}
		});
		btnNewButton_1.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(304, 110, 183, 30);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(304, 176, 183, 30);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
	
	public void limpiarFormulario() {
		textField.setText("");
		textField_1.setText("");
		bg.clearSelection();
	}
}