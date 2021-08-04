package EstructuraGRAFO;

import Objetos.Ruta;

public class Arista {
	
	private Vertice origen;
	private Vertice fin;
	private Double distancia;
	private Integer duracionViaje;
	private Integer cantMaxPasajeros;
	private String estado;
	private Double costo;

	public Arista() {
		
	}
	
	public Arista(Vertice o, Vertice destino) {
		this();
		this.origen = o;
		this.fin = destino;
	}

	public Arista(Vertice origen, Vertice fin, Double distancia, Integer duracionViaje, Integer cantMaxPasajeros,
			String estado, Double costo) {
		this(origen, fin);
		this.distancia = distancia;
		this.duracionViaje = duracionViaje;
		this.cantMaxPasajeros = cantMaxPasajeros;
		this.estado = estado;
		this.costo = costo;
	}
	
	public Arista(Ruta r) {
		this.origen = new Vertice(r.getOrigen());
		this.fin = new Vertice(r.getDestino());
		this.distancia = r.getDistancia();
		this.duracionViaje = r.getDuracionViaje();
		this.cantMaxPasajeros = r.getCantMaxPasajeros();
		this.estado = r.getEstado();
		this.costo = r.getCosto();
	}
	
	public Arista(Vertice v1, Vertice v2, Ruta r) {
		this.origen = v1;
		this.fin = v2;
		this.costo = r.getCosto();
		this.cantMaxPasajeros = r.getCantMaxPasajeros();
		this.duracionViaje = r.getDuracionViaje();
		this.estado = r.getEstado();
		this.distancia = r.getDistancia();
	}

	public Vertice getOrigen() {
		return origen;
	}

	public void setOrigen(Vertice origen) {
		this.origen = origen;
	}

	public Vertice getFin() {
		return fin;
	}

	public void setFin(Vertice fin) {
		this.fin = fin;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Integer getDuracionViaje() {
		return duracionViaje;
	}

	public void setDuracionViaje(Integer duracionViaje) {
		this.duracionViaje = duracionViaje;
	}

	public Integer getCantMaxPasajeros() {
		return cantMaxPasajeros;
	}

	public void setCantMaxPasajeros(Integer cantMaxPasajeros) {
		this.cantMaxPasajeros = cantMaxPasajeros;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	
	
	 
}
