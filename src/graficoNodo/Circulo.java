package graficoNodo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Circulo {

	int x,y;
	String id;
	
	String imagen = "C:\\Users\\mati9\\Desktop\\esfera.png";
	Image image;
	
	public Circulo(String id, int x, int y) {
		this.id = id;
		this.x=x;
        this.y=y;
        image = Toolkit.getDefaultToolkit().getImage(imagen);
	}
	
	public void pintar(Graphics g, Dibujo l) {
		g.drawImage(image, x-15, y-15, l);
        g.setColor(Color.black);
        g.drawString(id, x-10, y-10);
	}
	
	public boolean tirarPor(Point d) {
		if(d.distance(x, y)<=15) {
			return true;
		}else {
			return false;
		}
	}
	
	public void transladar(int dx, int dy) {
		this.x+=dx;
		this.y+=dy;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
