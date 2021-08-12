package Objetos;


public class Linea{
	
	private String nombre;
	private String color;
	private String estado;
	
	public Linea(String col, String nom, String est) {
		this.color = col;
		this.nombre = nom;
		this.estado = est;
	}
	
	public Linea(String c) {
		this.color = c;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
