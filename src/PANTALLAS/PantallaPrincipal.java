package PANTALLAS;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton gestionarEstbtn;
	private JButton gestionarLineabtn;
	private JButton comprarBoletobtn;
	private int tamAlto = 550;
	private int tamAncho = 800;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		setBackground(new Color(128, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, tamAncho, tamAlto);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Pantalla pricipal");
		
		//llamo a la pantalla de "Gestionar Estaciones"
		JFrame_CU1 jf = new JFrame_CU1(); 
		jf.setVisible(false);
		jf.setTitle("Gestionar estaciones");
		jf.setSize(tamAncho, tamAlto); 
		jf.setLocationRelativeTo(null);
		jf.setModal(true);
		
		//llamo a la pantalla de "Gestionar Líneas"
		JFrame_CU3 jf3 = new JFrame_CU3(); 
		jf3.setVisible(false);
		jf3.setTitle("Gestionar líneas de transporte");
		jf3.setSize(tamAncho, tamAlto); 
		jf3.setLocationRelativeTo(null);
		jf3.setModal(true);
		
		//llamo a la pantalla de "Vender Boleto"
		VenderBoleto vb = new VenderBoleto(); 
		vb.setVisible(false);
		vb.setTitle("Gestionar líneas de transporte");
		vb.setSize(tamAncho, tamAlto); 
		vb.setLocationRelativeTo(null);
		vb.setModal(true);
				
		JLabel lblNewLabel = new JLabel("Sistema de gesti\u00F3n de transporte");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setBounds(10, 11, 600, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione un medio de transporte: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setBounds(126, 90, 335, 23);
		contentPane.add(lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Tren");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gestionarEstbtn.setVisible(true);
				gestionarLineabtn.setVisible(true);
				comprarBoletobtn.setVisible(true);
				
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNewRadioButton.setForeground(new Color(102, 0, 0));
		rdbtnNewRadioButton.setBackground(new Color(255, 255, 204));
		rdbtnNewRadioButton.setBounds(210, 120, 114, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Colectivo");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gestionarEstbtn.setVisible(true);
				gestionarLineabtn.setVisible(true);
				comprarBoletobtn.setVisible(true);
				
			}
		});
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNewRadioButton_1.setForeground(new Color(102, 0, 0));
		rdbtnNewRadioButton_1.setBackground(new Color(255, 255, 204));
		rdbtnNewRadioButton_1.setBounds(337, 120, 124, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Subte");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gestionarEstbtn.setVisible(true);
				gestionarLineabtn.setVisible(true);
				comprarBoletobtn.setVisible(true);
				
			}
		});
		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNewRadioButton_2.setBackground(new Color(255, 255, 204));
		rdbtnNewRadioButton_2.setForeground(new Color(102, 0, 0));
		rdbtnNewRadioButton_2.setBounds(486, 120, 124, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_2);
		
		gestionarEstbtn = new JButton("Gestionar estaciones");
		gestionarEstbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jf.setVisible(true);
				
			}
		});
		gestionarEstbtn.setForeground(new Color(255, 255, 204));
		gestionarEstbtn.setBackground(new Color(102, 0, 0));
		gestionarEstbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		gestionarEstbtn.setBounds(225, 212, 306, 30);
		contentPane.add(gestionarEstbtn);
		gestionarEstbtn.setVisible(false);
		
		gestionarLineabtn = new JButton("Gestionar l\u00EDneas de transporte");
		gestionarLineabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jf3.setVisible(true);
			}
		});
		gestionarLineabtn.setBackground(new Color(102, 0, 0));
		gestionarLineabtn.setForeground(new Color(255, 255, 204));
		gestionarLineabtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		gestionarLineabtn.setBounds(225, 292, 306, 30);
		contentPane.add(gestionarLineabtn);
		gestionarLineabtn.setVisible(false);
		
		comprarBoletobtn = new JButton("Comprar boleto");
		comprarBoletobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				vb.setVisible(true);
			}
		});
		comprarBoletobtn.setBackground(new Color(102, 0, 0));
		comprarBoletobtn.setForeground(new Color(255, 255, 204));
		comprarBoletobtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		comprarBoletobtn.setBounds(225, 372, 306, 30);
		contentPane.add(comprarBoletobtn);
		comprarBoletobtn.setVisible(false);
	}
}
