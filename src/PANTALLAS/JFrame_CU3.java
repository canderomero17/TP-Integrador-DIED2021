package PANTALLAS;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JFrame_CU3 extends JDialog {

	private JPanel contentPane;
	private int tamAlto = 550;
	private int tamAncho = 800;

	/**
	 * Create the frame.
	 */
	
	public JFrame_CU3() {
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//llamo al panel de eliminarLinea
		EliminarLinea elimlin = new EliminarLinea();
		elimlin.setTitle("Eliminar linea");
		elimlin.setVisible(false);
		elimlin.setSize(tamAncho, tamAlto);
		elimlin.setLocationRelativeTo(null);
		elimlin.setModal(true);
		
		//llamo al panel de "buscarLinea"
		BuscarLinea bl = new BuscarLinea();
		bl.setTitle("Buscar Línea");
		bl.setVisible(false);
		bl.setSize(tamAncho, tamAlto);
		bl.setLocationRelativeTo(null);
		bl.setModal(true);
		
		//llamo al panel de "editarLinea"
		EditarLinea edl = new EditarLinea();
		edl.setTitle("Editar Linea de Transporte");
		edl.setVisible(false);
		edl.setSize(tamAncho, tamAlto);
		edl.setLocationRelativeTo(null);
		edl.setModal(true);  
		
		//llamo al panel de "agregarLinea"
		AgregarLineaTransporte alt = new AgregarLineaTransporte();
		alt.setTitle("Agregar Linea de Transporte");
		alt.setVisible(false);
		alt.setSize(tamAncho, tamAlto);
		alt.setLocationRelativeTo(null);
		alt.setModal(true);
		
		//llamo al panel de "registrarRuta"
		RegistrarRuta rr = new RegistrarRuta();
		rr.setTitle("Registrar rutas");
		rr.setVisible(false);
		rr.setSize(tamAncho, tamAlto);
		rr.setLocationRelativeTo(null);
		rr.setModal(true);
		
		JButton btnNewButton = new JButton("Agregar l\u00EDnea");
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			alt.setVisible(true);
			}
		});
		
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(180, 125, 200, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar l\u00EDnea");
		btnNewButton_1.setForeground(new Color(255, 255, 204));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elimlin.setVisible(true);
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBackground(new Color(102, 0, 0));
		btnNewButton_1.setBounds(410, 125, 200, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Editar l\u00EDnea");
		btnNewButton_2.setForeground(new Color(255, 255, 204));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edl.setVisible(true); 
			}
		});
		
		btnNewButton_2.setBackground(new Color(102, 0, 0));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(180, 225, 200, 30);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Buscar l\u00EDnea");
		btnNewButton_3.setForeground(new Color(255, 255, 204));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bl.setVisible(true); 
			}
		});
		
		btnNewButton_3.setBackground(new Color(102, 0, 0));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setBounds(410, 225, 200, 30);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Gestionar l\u00EDneas de transporte");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 600, 45);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton_5 = new JButton("Registrar trayectos y/o rutas");
		btnNewButton_5.setForeground(new Color(255, 255, 204));
		btnNewButton_5.setBackground(new Color(102, 0, 0));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rr.setVisible(true);
			}
		});
		
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_5.setBounds(180, 325, 430, 30);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_4 = new JButton("Volver");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_4.setForeground(new Color(255, 255, 204));
		btnNewButton_4.setBackground(new Color(102, 0, 0));
		btnNewButton_4.setBounds(666, 470, 108, 30);
		contentPane.add(btnNewButton_4);
	}
}