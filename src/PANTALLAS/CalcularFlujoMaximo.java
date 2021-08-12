package PANTALLAS;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog; 
import javax.swing.JScrollPane; 
import javax.swing.table.DefaultTableModel; 
import EstructuraGRAFO.Grafo;
import EstructuraGRAFO.Vertice;
import Objetos.Estacion;
import TP_DAO.EstacionDAO_SQL;
import TP_DAO.EstacionesDAO;
import TP_Util.ConnectionPostgres; 
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane; 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class CalcularFlujoMaximo extends JDialog {
	
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxEstacionDestino;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxEstacionOrigen;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	private DefaultTableModel model = new DefaultTableModel();
	
	private ArrayList<String> listEstacionOrigen = new ArrayList<String>();
	private ArrayList<String> listEstacionDestino = new ArrayList<String>();
	private JTable table;
	private JScrollPane js;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CalcularFlujoMaximo dialog = new CalcularFlujoMaximo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CalcularFlujoMaximo() {
		getContentPane().setBackground(new Color(255, 255, 204));
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		
		Grafo grafo = new Grafo(); 
		grafo.armarGrafoEstacion();
		
		JLabel lblNewLabel = new JLabel("C\u00E1lculo de flujo m\u00E1ximo");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 600, 45);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Estaci\u00F3n origen:");
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(18, 68, 138, 19);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Estaci\u00F3n destino:");
		lblNewLabel_2.setForeground(new Color(102, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(284, 68, 138, 19);
		getContentPane().add(lblNewLabel_2);
		
		comboBoxEstacionOrigen = new JComboBox();
		comboBoxEstacionOrigen.setBackground(new Color(102, 0, 0));
		comboBoxEstacionOrigen.setForeground(new Color(255, 255, 204));
		comboBoxEstacionOrigen.setBounds(166, 68, 108, 24);
		getContentPane().add(comboBoxEstacionOrigen);
		
		comboBoxEstacionDestino = new JComboBox();
		comboBoxEstacionDestino.setForeground(new Color(255, 255, 204));
		comboBoxEstacionDestino.setBackground(new Color(102, 0, 0));
		comboBoxEstacionDestino.setBounds(432, 68, 108, 24);
		getContentPane().add(comboBoxEstacionDestino);
		
		table = new JTable();
		table.setBounds(18, 109, 532, 56);
		
		js=new JScrollPane(table);
		js.setBounds(18, 111, 750, 178);
		js.setVisible(true);
		getContentPane().add(js);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[] {"Trayecto","Caminos"});
		
		btnNewButton_2 = new JButton("Calcular flujo m\u00E1ximo");
		btnNewButton_2.setBackground(new Color(102, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 204));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					 
				Estacion nuevaEstacionOrigen = new Estacion(listEstacionOrigen.get(0), listEstacionOrigen.get(1), listEstacionOrigen.get(2), listEstacionOrigen.get(3), listEstacionOrigen.get(4));
				Estacion nuevaEstacionDestino = new Estacion(listEstacionDestino.get(0), listEstacionDestino.get(1), listEstacionDestino.get(2), listEstacionDestino.get(3), listEstacionDestino.get(4));
				 
				List<Vertice> listaCaminos = new ArrayList<Vertice>();
				ArrayList<String> caminosMostrar = new ArrayList<String>();
				listaCaminos = grafo.flujoMaximo(nuevaEstacionOrigen, nuevaEstacionDestino);
				
	 			model.setRowCount(0);
			
				String mostrar ="";
				for(int i=0; i<listaCaminos.size(); i++) {
						caminosMostrar.add(listaCaminos.get(i).getEst().getId()+" --> ");
				}
					for(int t=0; t<caminosMostrar.size();t++) {
						mostrar += caminosMostrar.get(t)+" ";
					}
					model.addRow(new Object [] {"Trayecto "+(1),mostrar});
					caminosMostrar.clear();
					mostrar = ""; 
				table.setModel(model); 
				
				lblNewLabel_5.setVisible(true);
				lblNewLabel_6.setVisible(true);
				lblNewLabel_6.setText(grafo.calcularFlujoMaximo(listaCaminos).toString()); 
			}  
			 
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(284, 308, 239, 30);
		getContentPane().add(btnNewButton_2);
		btnNewButton_2.setVisible(false);
		
		lblNewLabel_3 = new JLabel("Flujo m\u00E1ximo:");
		lblNewLabel_3.setForeground(new Color(102, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(18, 144, 138, 19);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(166, 144, 500, 19);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				dispose();
			}
		});
		btnNewButton.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButtonMC = new JButton("Mostrar caminos");
		btnNewButtonMC.setBackground(new Color(102, 0, 0));
		btnNewButtonMC.setForeground(new Color(255, 255, 204));
		btnNewButtonMC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.setRowCount(0);
				listEstacionOrigen.clear();
				listEstacionDestino.clear();
				grafo.armarGrafoEstacion(); 
				
				String origen = comboBoxEstacionOrigen.getSelectedItem().toString();
				String destino = comboBoxEstacionDestino.getSelectedItem().toString();
				
				ResultSet r = null;
				ResultSet r1 = null;
				EstacionesDAO estacionDao = new EstacionDAO_SQL();
					
				
				r = estacionDao.consultarDatos("estaciones","id", origen);
				r1 = estacionDao.consultarDatos("estaciones", "id", destino);
					try {
						while(r.next()) {
							listEstacionOrigen.add(r.getString("id"));
							listEstacionOrigen.add(r.getString("nombre"));
							listEstacionOrigen.add(r.getString("horarioap"));
							listEstacionOrigen.add(r.getString("horariocierre"));
							listEstacionOrigen.add(r.getString("estado"));
						}
						while(r1.next()) {
							listEstacionDestino.add(r1.getString("id"));
							listEstacionDestino.add(r1.getString("nombre"));
							listEstacionDestino.add(r1.getString("horarioap"));
							listEstacionDestino.add(r1.getString("horariocierre"));
							listEstacionDestino.add(r1.getString("estado"));
						}
					}catch(Exception exc){
						exc.printStackTrace();
					}
						
					Estacion nuevaEstacionOrigen = new Estacion(listEstacionOrigen.get(0), listEstacionOrigen.get(1), listEstacionOrigen.get(2), listEstacionOrigen.get(3), listEstacionOrigen.get(4));
					Estacion nuevaEstacionDestino = new Estacion(listEstacionDestino.get(0), listEstacionDestino.get(1), listEstacionDestino.get(2), listEstacionDestino.get(3), listEstacionDestino.get(4));

					List<List<Vertice>> listaCaminos = new ArrayList<List<Vertice>>();
					listaCaminos = grafo.caminos(nuevaEstacionOrigen, nuevaEstacionDestino);
						
						ArrayList<String> caminosMostrar = new ArrayList<String>();
							
						String mostrar ="";
						for(int i=0; i<listaCaminos.size(); i++) {
							for(int j=0; j<listaCaminos.get(i).size(); j++) {
								caminosMostrar.add(listaCaminos.get(i).get(j).getEst().getId()+" --> ");
							}
							for(int t=0; t<caminosMostrar.size();t++) {
								mostrar += caminosMostrar.get(t)+" ";
							}
							 
							model.addRow(new Object [] {"Trayecto "+(i+1),mostrar});
							caminosMostrar.clear();
							mostrar = "";
							} 
						table.setModel(model);
						
						btnNewButton_2.setVisible(true);
					 
			}
		});
		btnNewButtonMC.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButtonMC.setBounds(583, 61, 185, 36);
		getContentPane().add(btnNewButtonMC);
		
		lblNewLabel_5 = new JLabel("Flujo m\u00E1ximo:");
		lblNewLabel_5.setForeground(new Color(102, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(277, 378, 145, 25);
		getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		lblNewLabel_6 = new JLabel(" ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_6.setForeground(new Color(102, 0, 0));
		lblNewLabel_6.setBounds(430, 378, 151, 25);
		getContentPane().add(lblNewLabel_6);
		lblNewLabel_6.setVisible(false);
		
		try {
			Connection cn = ConnectionPostgres.conectar();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM estaciones ORDER BY id");
			while(rs.next()) {
				this.comboBoxEstacionOrigen.addItem(rs.getString("id"));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		try {
			Connection cn = ConnectionPostgres.conectar();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM estaciones ORDER BY id");
			while(rs.next()) {
				this.comboBoxEstacionDestino.addItem(rs.getString("id"));
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	public void limpiarFormulario() {
		comboBoxEstacionDestino.setSelectedIndex(0);
		comboBoxEstacionOrigen.setSelectedIndex(0);   
		model.setRowCount(0);
		btnNewButton_2.setVisible(false);
		lblNewLabel_6.setText("");
		lblNewLabel_5.setVisible(false); 
		listEstacionOrigen.clear();
		listEstacionDestino.clear();
	}
}
