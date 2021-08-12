package Objetos;
 

public class Trayecto<T> {
	
	private String idTrayecto;
	private Estacion origen;
	private Estacion destino;
	private Double distanciaTotal;
	private Integer duracionTotal;
	private Double costoTotal;
	private String colorLinea;
	
	public Trayecto() {}

	public Trayecto(String id, Estacion origen, Estacion dest, Double dist, Integer duracion, Double costo, String l) {
		
		this.idTrayecto = id;
		this.origen = origen;
		this.destino = dest;
		this.distanciaTotal = dist;
		this.duracionTotal = duracion;
		this.costoTotal = costo;
		this.colorLinea = l;
	}
	
	public Trayecto(String id, String l, Estacion origen, Estacion destino) {
		
		this.idTrayecto = id;
		this.origen = origen;
		this.destino = destino;
		this.colorLinea = l;
	}
	
	public String getIdCamino() {
		return idTrayecto;
	}

	public void setIdCamino(String idCamino) {
		this.idTrayecto = idCamino;
	}

	public Estacion getOrigen() {
		return origen;
	}

	public void setOrigen(Estacion origen) {
		this.origen = origen;
	}

	public Estacion getDestino() {
		return destino;
	}

	public void setDestino(Estacion destino) {
		this.destino = destino;
	}

	public Double getDistanciaTotal() {
		return distanciaTotal;
	}

	public void setDistanciaTotal(Double distanciaTotal) {
		this.distanciaTotal = distanciaTotal;
	}

	public Integer getDuracionTotal() {
		return duracionTotal;
	}

	public void setDuracionTotal(Integer duracionTotal) {
		this.duracionTotal = duracionTotal;
	}

	public Double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(Double costoTotal) {
		this.costoTotal = costoTotal;
	}

	public String getColorLinea() {
		return colorLinea;
	}

	public void setColorLinea(String colorLinea) {
		this.colorLinea = colorLinea;
	}

/*	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}


	public void agregarRuta(Ruta r) {
		this.rutas.add(r);
	}
	*/
	
}
