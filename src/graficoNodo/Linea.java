package graficoNodo;

import java.awt.Color;
import java.awt.Graphics;

public class Linea {

	Circulo nodoInicial;
	Circulo nodoFinal;
	Color color;
	//Color color = Color.BLACK;
	
	public Linea(Circulo nodoInicial, Circulo nodoFinal, Color color) {
		this.nodoFinal = nodoFinal;
		this.nodoInicial = nodoInicial;
		this.color = color;
	}
	
	public void painter(Graphics g) {
		g.setColor(Color.red);
        g.setColor(this.color);
        g.drawLine(nodoInicial.getX(), nodoInicial.getY(), nodoFinal.getX(), nodoFinal.getY());
	}

	public Circulo getNodoInicial() {
		return nodoInicial;
	}
	public void setNodoInicial(Circulo nodoInicial) {
		this.nodoInicial = nodoInicial;
	}
	public Circulo getNodoFinal() {
		return nodoFinal;
	}
	public void setNodoFinal(Circulo nodoFinal) {
		this.nodoFinal = nodoFinal;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
