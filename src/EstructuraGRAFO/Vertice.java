package EstructuraGRAFO;

import Objetos.Estacion;

public class Vertice {
	
	private Estacion est;
	
	public  Vertice() {
		
	}
	
	public Vertice(Estacion e) {
		this.est = e;
	}

	public Estacion getEst() {
		return est;
	}

	public void setEst(Estacion est) {
		this.est = est;
	}
	
	

}
