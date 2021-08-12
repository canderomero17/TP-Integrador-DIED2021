package PANTALLAS;
 
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JDialog; 
import javax.swing.JScrollPane; 
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import EstructuraGRAFO.Grafo;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class PageRank extends JDialog {
	
	private JTable table;
	  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageRank frame = new PageRank();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PageRank() {
		getContentPane().setBackground(new Color(255, 255, 204));
		setBounds(100, 100, 800, 550);
		
		Grafo grafo = new Grafo();
		grafo.armarGrafoEstacion();
		 
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 140, 764, 216);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(10, 140, 764, 216);
		getContentPane().add(js);
		js.setVisible(true);
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.setColumnIdentifiers(new Object[] {"Estación", "PageRank"});
		
		table.setModel(model);
		
		String[] dato = new String[2];
		int contador = 0;
		
		while(contador < grafo.getVertices().size()) {
			dato[0] = "Estacion: " + grafo.getVertices().get(contador).getEst().getId();
			dato[1] = String.valueOf((grafo.rankPages()[contador]));
			model.addRow(new Object [] { dato[0], dato[1] });
			contador++;
		}
		
		TableRowSorter<TableModel> tablita = new TableRowSorter<TableModel>(model);
		table.setRowSorter(tablita);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setForeground(new Color(255, 255, 204));
		btnNewButton.setBackground(new Color(102, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(666, 470, 108, 30);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("PageRank");
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 600, 45);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Estaciones ordenadas seg\u00FAn el algoritmo PageRank:");
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 107, 764, 23);
		getContentPane().add(lblNewLabel_1);
	}
}
