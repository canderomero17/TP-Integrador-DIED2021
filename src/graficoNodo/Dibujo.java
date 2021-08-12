package graficoNodo;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import PANTALLAS.PantallaGrafica;

@SuppressWarnings("serial")
public class Dibujo extends JPanel implements MouseListener, MouseMotionListener{

	int x=0, y=0;
	Linea linea;
	Circulo circulo;
	Circulo tirar = null;
	PantallaGrafica p;
	
	List<Circulo> listCirculo = new ArrayList<Circulo>();
	List<Linea> listarista = new ArrayList<Linea>();
	
	public Dibujo(PantallaGrafica prin) {
		p=prin;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void agregarCirculo(String id, int x, int y) {
		 circulo = new Circulo(id,x,y);
	     listCirculo.add(circulo);
	     repaint();
	     p.repaint();
	}
	
	public void conectarDosCirculos(int x, int y, Color color) {
		 try{
			 linea = new Linea(listCirculo.get(x),listCirculo.get(y), color);
			 this.listarista.add(linea);
			 repaint();
	         p.repaint();
	         
	        }catch(IndexOutOfBoundsException e){
	            JOptionPane.showMessageDialog(null, "No se encontro circulo");
	        }
	}
	
	 public void paintComponent(Graphics g){
		 super.paintComponents(g);
	     for (Linea f: listarista){
	       f.painter(g);
	      }
	     for (Circulo f: listCirculo){
	    	 f.pintar(g,this);
	    	 }
	     }
	 
	 public void mostrarCirculo(String id) {
	    	int x = (int) (Math.random()*750+30); 
	    	int y = (int) (Math.random()*450+30); 
	    	if(listCirculo.size()>0) {
	    		for(int i=0; i<listCirculo.size(); i++) {
	    			if(listCirculo.get(i).getX() < x+40 && listCirculo.get(i).getX() > x-40 && listCirculo.get(i).getY() < y+40 && listCirculo.get(i).getY() > y-40) {
	    				mostrarCirculo(id);
	    			}else {
	    				agregarCirculo(id, x, y);
	    				break;
	    			}
	    				
	    		}
	    	}else {
	    		agregarCirculo(id, x, y);
	    	}
	    	
	    }
	 
	 public void conectarCirculos(String Idcirculo1 , String Idcirculo2, Color color) {
		 int circulo1=0, circulo2=0;
		 for(int i=0; i<listCirculo.size(); i++) {
			 if(listCirculo.get(i).getId().equals(Idcirculo1)) {
				 circulo1 = i;
			 }
			 if(listCirculo.get(i).getId().equals(Idcirculo2)) {
				 circulo2 = i;
			 }
		 }
		
		 conectarDosCirculos(circulo1, circulo2, color);
	    }
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(tirar==null) {
			for(Circulo f: listCirculo) {
				if(f.tirarPor(e.getPoint())) {
					tirar=f;
				}
				x=e.getPoint().x;
				y=e.getPoint().y;
				repaint();
				p.repaint();
			}
		}
		else {
			tirar.transladar(e.getPoint().x-x, e.getPoint().y-y);
			 x=e.getPoint().x;
	         y=e.getPoint().y;
	         repaint();
	         p.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		tirar=null;
	}
}
