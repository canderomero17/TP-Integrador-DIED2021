package Objetos;

public class Estacion {
	
	private String id;
	private String nombre;
	private String horarioApertura;
	private String horarioCierre;
	private String estado;  
	
	
	public Estacion() {
		
	}
	
	public Estacion(String id) {
		this.id = id;
	}
	
	public Estacion(String i, String nom, String horarioA, String horarioC, String est) {
		this.id = i;
		this.nombre = nom;
		this.horarioApertura = horarioA;
		this.horarioCierre = horarioC;
		this.estado = est;
	}
	
	public Estacion(Estacion e) {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHorarioApertura() {
		return horarioApertura;
	}

	public void setHorarioApertura(String horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public String getHorarioCierre() {
		return horarioCierre;
	}

	public void setHorarioCierre(String horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
