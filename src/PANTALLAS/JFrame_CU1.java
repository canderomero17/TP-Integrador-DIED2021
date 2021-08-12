package PANTALLAS;

import java.awt.Color; 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog; 
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JFrame_CU1 extends JDialog {

	private JPanel contentPane; 
	private int tamAlto = 550;
	private int tamAncho = 800;
	
	
	public JFrame_CU1() {
		setBackground(new Color(255, 255, 204)); 
		 
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204)); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		setLocationRelativeTo(null);
		
		//llamo al panel de "agregarEstacion"
		AgregarEstacion ae = new AgregarEstacion();
		ae.setVisible(false);
		ae.setTitle("Agregar Estación");
		ae.setSize(tamAncho, tamAlto);
		ae.setLocationRelativeTo(null);
		ae.setModal(true); 
		
		//llamo al panel de "eliminarEstacion"
		EliminarEstacion ee = new EliminarEstacion();
		ee.setVisible(false);
		ee.setTitle("Eliminar Estación");
		ee.setSize(tamAncho, tamAlto);
		ee.setLocationRelativeTo(null);
		ee.setModal(true); 
		
		//llamo al panel de "editarEstacion"
		EditarEstacion ed = new EditarEstacion();
		ed.setVisible(false);
		ed.setTitle("Editar Estación");
		ed.setSize(tamAncho, tamAlto);
		ed.setLocationRelativeTo(null);
		ed.setModal(true); 
		
		//llamo al panel de "buscarEstacion"
		BuscarEstacion be = new BuscarEstacion();
		be.setVisible(false);
		be.setTitle("Buscar Estación");
		be.setSize(tamAncho, tamAlto);
		be.setLocationRelativeTo(null);
		be.setModal(true); 
		
		//llamo al panel de "ConsultarEstadoEstacion"
		ConsultarEstadoEstacion cee = new ConsultarEstadoEstacion();
		cee.setVisible(false);
		cee.setTitle("Consultar estado Estación");
		cee.setSize(tamAncho, tamAlto);
		cee.setLocationRelativeTo(null);
		cee.setModal(true); 
		
		//llamo al panel de "PageRank"
		PageRank pr = new PageRank();
		pr.setVisible(false);
		pr.setTitle("PageRank");
		pr.setSize(tamAncho, tamAlto);
		pr.setLocationRelativeTo(null);
		pr.setModal(true); 
		
		//llamo al panel de "Flujo máximo"
		CalcularFlujoMaximo fm = new CalcularFlujoMaximo();
		fm.setVisible(false);
		fm.setTitle("Flujo máximo");
		fm.setSize(tamAncho, tamAlto);
		fm.setLocationRelativeTo(null);
		fm.setModal(true);
		
		//llamo al panel de "Proximo mantenimiento"
				ProximoMantenimiento pm = new ProximoMantenimiento();
				pm.setVisible(false);
				pm.setTitle("Proximo Mantenimiento");
				pm.setSize(tamAncho, tamAlto);
				pm.setLocationRelativeTo(null);
				pm.setModal(true);
		
		//agrego boton de "AGREGAR ESTACIÓN"
				JButton btnNewButton = new JButton("Agregar estaci\u00F3n");
				btnNewButton.setForeground(new Color(255, 255, 204));
				btnNewButton.setBackground(new Color(102, 0, 0));
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ae.setVisible(true);
					}
			
				});
				btnNewButton.setBounds(180, 110, 220, 30);
				contentPane.add(btnNewButton);
				
		//agrego boton de "ELIMINAR ESTACIÓN"
				JButton btnNewButton_3 = new JButton("Eliminar estaci\u00F3n");
				btnNewButton_3.setForeground(new Color(255, 255, 204));
				btnNewButton_3.setBackground(new Color(102, 0, 0));
				btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ee.setVisible(true);
					}
				});
				btnNewButton_3.setBounds(410, 110, 220, 30);
				contentPane.add(btnNewButton_3);
				
			//agrego el boton de "EDITAR ESTACIÓN"
				JButton btnNewButton_1 = new JButton("Editar estaci\u00F3n");
				btnNewButton_1.setForeground(new Color(255, 255, 204));
				btnNewButton_1.setBackground(new Color(102, 0, 0));
				btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ed.setVisible(true);
					}
				});
				btnNewButton_1.setBounds(180, 215, 220, 30);
				contentPane.add(btnNewButton_1);
				
			//agrego el botón de "BUSCAR ESTACIÓN"
				JButton btnNewButton_2 = new JButton("Buscar estaci\u00F3n");
				btnNewButton_2.setForeground(new Color(255, 255, 204));
				btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton_2.setBackground(new Color(102, 0, 0));
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						be.setVisible(true);
					}
				});
				btnNewButton_2.setBounds(410, 215, 220, 30);
				contentPane.add(btnNewButton_2);
				
				JLabel lblNewLabel = new JLabel("Gestionar estaciones");
				lblNewLabel.setForeground(new Color(102, 0, 0));
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
				lblNewLabel.setBounds(10, 11, 600, 45);
				contentPane.add(lblNewLabel);
				
				//agrego el boton de "CONSULTAR Y EDITAR ESTACIÓN"
				JButton btnNewButton_4 = new JButton("Registrar tareas");
				btnNewButton_4.setForeground(new Color(255, 255, 204));
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cee.setVisible(true);
					}
				});
				btnNewButton_4.setBackground(new Color(102, 0, 0));
				btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton_4.setBounds(180, 315, 220, 30);
				contentPane.add(btnNewButton_4);
				
				//agrego el boton de "PAGE RANK"
				JButton btnNewButton_5 = new JButton("PageRank");
				btnNewButton_5.setForeground(new Color(255, 255, 204));
				btnNewButton_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pr.setVisible(true);
					}
				});
				btnNewButton_5.setBackground(new Color(102, 0, 0));
				btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton_5.setBounds(413, 315, 220, 30);
				contentPane.add(btnNewButton_5);
				
				JButton btnNewButton_6 = new JButton("Cancelar");
				btnNewButton_6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { 
						dispose();
					}
				});
				btnNewButton_6.setForeground(new Color(255, 255, 204));
				btnNewButton_6.setBackground(new Color(102, 0, 0));
				btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton_6.setBounds(666, 470, 108, 30);
				contentPane.add(btnNewButton_6);
				
				//agrego el boton de "FLUJO MÁXIMO"
				JButton btnNewButton_7 = new JButton("Flujo m\u00E1ximo");
				btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnNewButton_7.setForeground(new Color(255, 255, 204));
				btnNewButton_7.setBackground(new Color(102, 0, 0));
				btnNewButton_7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fm.setVisible(true);
					}
				});
				btnNewButton_7.setBounds(180, 415, 220, 30);
				contentPane.add(btnNewButton_7);
				
				//agrego el boton de "PROXIMO MANTENIMIENTO"
				JButton btnNewButton_8 = new JButton("Pr\u00F3ximo Mantenimiento");
				btnNewButton_8.setBackground(new Color(102, 0, 0));
				btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 15));
				btnNewButton_8.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pm.setVisible(true);
					}
				});
				btnNewButton_8.setForeground(new Color(255, 255, 204));
				btnNewButton_8.setBounds(413, 415, 220, 30);
				contentPane.add(btnNewButton_8);
				
	}
}
