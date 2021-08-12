package EstructuraGRAFO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

import Objetos.Estacion;
import Objetos.Ruta;
import Objetos.Trayecto;
import TP_DAO.GrafoDAO;
import TP_DAO.GrafoDAO_SQL;

public class Grafo {
	
	private List<Arista> aristas;
	private List<Vertice> vertices;
	@SuppressWarnings("rawtypes")
	private List<Trayecto> TrayectoColor;
	
	@SuppressWarnings("rawtypes")
	public void armarGrafoEstacion() {
		
		ResultSet r = null;
		ResultSet r1 = null;
		ResultSet col = null;
		
		Estacion nuevaEstacionOrigen = new Estacion();
		Estacion nuevaEstacionDestino = new Estacion();
		Trayecto nuevoTrayecto = new Trayecto();
		
		GrafoDAO grafoDao = new GrafoDAO_SQL();

		ArrayList<String> listEstacion = new ArrayList<String>();
		ArrayList<String> listRuta = new ArrayList<String>();
		ArrayList<String> color = new ArrayList<String>();
		
		this.aristas.clear();
		this.vertices.clear();
		
		r = grafoDao.mostrarTodosLosDatos("rutas");
		r1 = grafoDao.mostrarTodosLosDatos("estaciones");
		col = grafoDao.obtenerColor();

		try {
			while(r1.next()) {
				listEstacion.add(r1.getString("id"));
				listEstacion.add(r1.getString("nombre"));
				listEstacion.add(r1.getString("horarioap"));
				listEstacion.add(r1.getString("horariocierre"));
				listEstacion.add(r1.getString("estado"));
				nuevaEstacionOrigen = new Estacion(listEstacion.get(0), listEstacion.get(1), listEstacion.get(2), listEstacion.get(3), listEstacion.get(4));
				this.agregarNodoEstacion(nuevaEstacionOrigen);
				listEstacion.clear();
				}
			}catch(Exception exc){
				exc.printStackTrace();
				}
		try {
			while(col.next()) {
				color.add(col.getString("idtrayecto"));
				color.add(col.getString("color"));
				color.add(col.getString("origen"));
				color.add(col.getString("destino"));
				nuevaEstacionOrigen = new Estacion(color.get(2));
				nuevaEstacionDestino = new Estacion(color.get(3));
				nuevoTrayecto = new Trayecto(color.get(0), color.get(1), nuevaEstacionOrigen, nuevaEstacionDestino);
				this.agregarTrayecto(nuevoTrayecto);
				color.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			while(r.next()) {
				listRuta.add(r.getString("id"));
				listRuta.add(r.getString("origen"));
				listRuta.add(r.getString("destino")); //2
				listRuta.add(r.getDouble("distancia")+""); //3
				listRuta.add(r.getInt("duracionviaje")+"");
				listRuta.add(r.getInt("cantmaxpasajeros")+""); //5
				listRuta.add(r.getString("estado"));
				listRuta.add(r.getDouble("costo")+""); //7
				
				for(int i=0; i<vertices.size(); i++) {
					if(listRuta.get(1).equals(vertices.get(i).getEst().getId())) {
						nuevaEstacionOrigen = new Estacion(vertices.get(i).getEst().getId(),vertices.get(i).getEst().getNombre(), vertices.get(i).getEst().getHorarioApertura(), vertices.get(i).getEst().getHorarioCierre(), vertices.get(i).getEst().getEstado());
					}
					if(listRuta.get(2).equals(vertices.get(i).getEst().getId())) {
						nuevaEstacionDestino = new Estacion(vertices.get(i).getEst().getId(),vertices.get(i).getEst().getNombre(), vertices.get(i).getEst().getHorarioApertura(), vertices.get(i).getEst().getHorarioCierre(), vertices.get(i).getEst().getEstado());
					}
				}
				Ruta ruta = new Ruta(listRuta.get(0), nuevaEstacionOrigen, nuevaEstacionDestino, Double.parseDouble(listRuta.get(3)), Integer.parseInt(listRuta.get(4)), Integer.parseInt(listRuta.get(5)), listRuta.get(6), Double.parseDouble(listRuta.get(7)));
				this.conectar(ruta);
				listRuta.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Grafo() {
		this.aristas = new ArrayList<Arista>();
		this.vertices = new ArrayList<Vertice>();
		this.TrayectoColor = new ArrayList<Trayecto>();
	}
	
	@SuppressWarnings("rawtypes")
	public List<Trayecto> getTrayectoColor() {
		return TrayectoColor;
	}

	@SuppressWarnings("rawtypes")
	public void setTrayectoColor(List<Trayecto> trayectoColor) {
		TrayectoColor = trayectoColor;
	}
	
	@SuppressWarnings("rawtypes")
	public void agregarTrayecto(Trayecto trayecto) {
		this.TrayectoColor.add(trayecto);
	}
	
	public List<Vertice> menorDuracion(Estacion origen, Estacion destino){
		List<List<Vertice>> lista = new ArrayList<List<Vertice>>();
		lista = this.caminos(origen, destino);
		List<Arista> listaA = new ArrayList<Arista>();
		listaA = this.getAristas();
		
		List<Integer> duracTotal = new ArrayList<Integer>();
		int aux = 0;
		int posCamino=0;
		
			for (int i=0; i<lista.size(); i++) {
				for(int j=0; j<lista.get(i).size()-1; j++) {
					for(int z=0; z<listaA.size(); z++) {
						
						if(lista.get(i).get(j).getEst().getId().equals(listaA.get(z).getOrigen().getEst().getId()) &&  lista.get(i).get(j+1).getEst().getId().equals(listaA.get(z).getFin().getEst().getId())) {
							aux = aux + listaA.get(z).getDuracionViaje(); 	
						}	
					}
				}
				duracTotal.add(aux);
				int aux1 = duracTotal.get(0);
				for(int r=0; r<duracTotal.size(); r++) {
					if(aux1 > duracTotal.get(r)) {
	  					aux1 = duracTotal.get(r);
	  					posCamino = r;
	  				}
				}
				aux=0;
			}
			return lista.get(posCamino);
	}
	
	public List<Vertice> menorDistancia(Estacion origen, Estacion destino){
		List<List<Vertice>> lista = new ArrayList<List<Vertice>>();
		lista = this.caminos(origen, destino);
		List<Arista> listaA = new ArrayList<Arista>();
		listaA = this.getAristas();
		
		List<Double> distanciaTotal = new ArrayList<Double>();
		Double aux = 0.0;
		int posCamino=0;
		
			for (int i=0; i<lista.size(); i++) {
				for(int j=0; j<lista.get(i).size()-1; j++) {
					for(int z=0; z<listaA.size(); z++) {
						
						if(lista.get(i).get(j).getEst().getId().equals(listaA.get(z).getOrigen().getEst().getId()) &&  lista.get(i).get(j+1).getEst().getId().equals(listaA.get(z).getFin().getEst().getId())) {
							aux = aux + listaA.get(z).getDistancia(); 	
						}	
					}
				}
				distanciaTotal.add(aux);
				Double aux1 = distanciaTotal.get(0);
				for(int r=0; r<distanciaTotal.size(); r++) {
					if(aux1 > distanciaTotal.get(r)) {
	  					aux1 = distanciaTotal.get(r);
	  					posCamino = r;
	  				}
				}
				aux=0.0;
			}
			return lista.get(posCamino);
	}
	
	public List<Vertice> masBarato(Estacion origen, Estacion destino){
		
		List<List<Vertice>> lista = new ArrayList<List<Vertice>>();
		lista = this.caminos(origen, destino);
	
		List<Arista> listaA = new ArrayList<Arista>();
		listaA = this.getAristas();
		
		List<Double> costoTotal = new ArrayList<Double>();
		Double aux = 0.0;
		int posCamino=0;
		
			for (int i=0; i<lista.size(); i++) {
				for(int j=0; j<lista.get(i).size()-1; j++) {
					for(int z=0; z<listaA.size(); z++) {
						
						if(lista.get(i).get(j).getEst().getId().equals(listaA.get(z).getOrigen().getEst().getId()) &&  lista.get(i).get(j+1).getEst().getId().equals(listaA.get(z).getFin().getEst().getId())) {
							aux = aux + listaA.get(z).getCosto(); 	
						}	
					}
				}
				costoTotal.add(aux);
				Double aux1 = costoTotal.get(0);
				for(int r=0; r<costoTotal.size(); r++) {
					if(aux1 > costoTotal.get(r)) {
	  					aux1 = costoTotal.get(r);
	  					posCamino = r;
	  				}
				}
				aux=0.0;
			}
			return lista.get(posCamino);
	}
	
	
	public Double calcularPrecioBoleto(List<Vertice> caminoElegido){
		 
		List<Arista> listaA = new ArrayList<Arista>();
		listaA = this.getAristas();
		 
		Double aux = 0.0; 
		
			for (int i=0; i<caminoElegido.size()-1; i++) {
				for(int j=0; j<listaA.size(); j++) { 
						
						if(caminoElegido.get(i).getEst().getId().equals(listaA.get(j).getOrigen().getEst().getId()) &&  caminoElegido.get(i+1).getEst().getId().equals(listaA.get(j).getFin().getEst().getId())) {
							aux = aux + listaA.get(j).getCosto(); 	
						}	
					}
				}
			 
			return aux;
	}
	
	public List<Vertice> flujoMaximo(Estacion origen, Estacion destino){
		List<List<Vertice>> lista = new ArrayList<List<Vertice>>();
		lista = this.caminos(origen, destino);
		List<Arista> listaA = new ArrayList<Arista>();
		listaA = this.getAristas();
		
		List<Integer> cantMaxPasajeros = new ArrayList<Integer>();
		int aux = 0;
		int posCamino=0;
		
			for (int i=0; i<lista.size(); i++) {
				for(int j=0; j<lista.get(i).size()-1; j++) {
					for(int z=0; z<listaA.size(); z++) {
						
						if(lista.get(i).get(j).getEst().getId().equals(listaA.get(z).getOrigen().getEst().getId()) &&  lista.get(i).get(j+1).getEst().getId().equals(listaA.get(z).getFin().getEst().getId())) {
							aux = aux + listaA.get(z).getCantMaxPasajeros(); 	
						}	
					}
				}
				cantMaxPasajeros.add(aux);
				int aux1 = cantMaxPasajeros.get(0);
				for(int r=0; r<cantMaxPasajeros.size(); r++) {
					if(aux1 < cantMaxPasajeros.get(r)) {
	  					aux1 = cantMaxPasajeros.get(r);
	  					posCamino = r;
	  				}
				}
				aux=0;
			}
			return lista.get(posCamino);
	}
	
	public Double calcularFlujoMaximo(List<Vertice> caminoElegido){
		 
		List<Arista> listaA = new ArrayList<Arista>();
		listaA = this.getAristas();
		 
		Double aux = 0.0; 
		
			for (int i=0; i<caminoElegido.size()-1; i++) {
				for(int j=0; j<listaA.size(); j++) { 
						
						if(caminoElegido.get(i).getEst().getId().equals(listaA.get(j).getOrigen().getEst().getId()) &&  caminoElegido.get(i+1).getEst().getId().equals(listaA.get(j).getFin().getEst().getId())) {
							aux = aux + listaA.get(j).getCantMaxPasajeros(); 	
						}	
					}
				}
			 
			return aux;
	}

	public List<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(List<Arista> aristas) {
		this.aristas = aristas;
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertice> vertices) {
		this.vertices = vertices;
	}
	
	
	public void agregarNodoEstacion(Estacion e) {
		this.agregarNodoEstacion(new Vertice(e));
	}
	
	public void agregarNodoEstacion(Vertice e) {
		this.vertices.add(e);
	}
	
	public void conectar(Ruta r) {
		this.conectar(new Arista(r));
	}
	
	public void conectar(Arista a) {
		this.aristas.add(a);
	}
	
	public Vertice getNodoEstacion(Estacion e){
		return this.vertices.get(this.vertices.indexOf(new Vertice(e)));
	} 
	
	public List<Integer> getAdyacente(int valor){
		Vertice unNodo = this.getVertices().get(valor);
		List<Integer> salida = new ArrayList<Integer>();
		
		for(Arista enlace : this.aristas) {
			if(enlace.getOrigen().equals(unNodo)) {
				salida.add(Integer.parseInt(enlace.getFin().getEst().getId()));
			}
		}
		return salida;
	}
	
	public List<Vertice> getAdyacente(Vertice unNodo){
		List<Vertice> salida = new ArrayList<Vertice>();
		for(Arista enlace : this.aristas) {
			if(enlace.getOrigen().getEst().getId().equals(unNodo.getEst().getId())) {
				salida.add(enlace.getFin());
			}
		} 
		return salida;
	}
	
	public void imprimirAristas(){
		System.out.println(this.aristas.toString());
	}
	
	public Integer gradoEntrada(Vertice vertice){
		Integer res = 0;
		for(Arista arista : this.aristas){
			if(arista.getFin().equals(vertice)) ++res;
		}
		return res;
	}

	public Integer gradoSalida(Vertice vertice){
		Integer res = 0;
		for(Arista arista : this.aristas){
			if(arista.getOrigen().equals(vertice)) ++res;
		}
		return res;
	}
	
	public List<Estacion> recorridoAnchura(Vertice inicio){ // Busqueda a lo ancho
		List<Estacion> resultado = new ArrayList<Estacion>();
		//estructuras auxiliares
		Queue<Vertice> pendientes = new LinkedList<Vertice>();
		HashSet<Vertice> marcados = new HashSet<Vertice>();
		marcados.add(inicio);
		pendientes.add(inicio);
		
		while(!pendientes.isEmpty()){
			Vertice actual = pendientes.poll();
			List<Vertice> adyacentes = this.getAdyacente(actual);
			resultado.add(actual.getEst());
			for(Vertice v : adyacentes){
				if(!marcados.contains(v)){ 
					pendientes.add(v);
					marcados.add(v);
				}
			}
		}		
		
		for(Estacion e : resultado) {
			System.out.println(e.getNombre());
		}
		
		return resultado;
 	}
	
	public List<Estacion> recorridoProfundidad(Vertice inicio){ // Busqueda en profundidad
		List<Estacion> resultado = new ArrayList<Estacion>();
		Stack<Vertice> pendientes = new Stack<Vertice>();
		HashSet<Vertice> marcados = new HashSet<Vertice>();
		marcados.add(inicio);
		pendientes.push(inicio);
		
		while(!pendientes.isEmpty()){
			Vertice actual = pendientes.pop();
			List<Vertice> adyacentes = this.getAdyacente(actual);
			resultado.add(actual.getEst());
			for(Vertice v : adyacentes){
				if(!marcados.contains(v)){ 
					pendientes.push(v);
					marcados.add(v);
				}
			}
		}	
		
		for(Estacion e : resultado) {
			System.out.println(e.getNombre());
		}

		return resultado;
 	}
 	
	public List<Estacion> recorridoTopologico(){
		List<Estacion> resultado = new ArrayList<Estacion>();
		Map<Vertice,Integer> gradosVertex = new HashMap<Vertice,Integer>();
		for(Vertice vert : this.vertices){
			gradosVertex.put(vert, this.gradoEntrada(vert));
		}
		while(!gradosVertex.isEmpty()){
		
			List<Vertice> nodosSinEntradas = gradosVertex.entrySet()
							.stream()
							.filter( x -> x.getValue()==0)
							.map( p -> p.getKey())
							.collect( Collectors.toList());
			
            if(nodosSinEntradas.isEmpty()) System.out.println("TIENE CICLOS");
            
			for(Vertice v : nodosSinEntradas){
            	resultado.add(v.getEst());
            	gradosVertex.remove(v);
            	for(Vertice ady: this.getAdyacente(v)){
            		gradosVertex.put(ady,gradosVertex.get(ady)-1);
            	}
            }
		}
		
		for(Estacion e : resultado) {
			System.out.println(e.getNombre());
		}
		
		return resultado;
 	}
	
	public boolean esAdyacente(Vertice v1,Vertice v2){
    	List<Vertice> ady = this.getAdyacente(v1);
        for(Vertice unAdy : ady){
        	if(unAdy.equals(v2)) return true;
        }
        return false;
    }
	 
	    public List<List<Vertice>> caminos (Estacion e1, Estacion e2){
			return this.caminos(new Vertice(e1), new Vertice(e2));
		}
		
		public List<List<Vertice>> caminos(Vertice v1,Vertice v2){
	    	
	    	List<List<Vertice>> salida = new ArrayList<List<Vertice>>();
	    	List<Vertice> visitados = new ArrayList<Vertice>();
	    	visitados.add(v1);
	    	buscarCaminosAux(v1,v2,visitados,salida);
	    
	    	List<Arista> listaA = new ArrayList<Arista>();
			listaA = this.getAristas();
			
			int contador = 0;
	    	
		 	while(contador <= salida.size()) {
				
				for(int i=0; i<salida.size(); i++) {
		    		for (int j=0; j<salida.get(i).size()-1; j++) {
		    			for(int z=0; z<listaA.size(); z++) {

		    				if(salida.get(i).get(j).getEst().getId().equals(listaA.get(z).getOrigen().getEst().getId()) &&  salida.get(i).get(j+1).getEst().getId().equals(listaA.get(z).getFin().getEst().getId())) {
								
		    					if(listaA.get(z).getEstado().equals("No activa")) {
		    							salida.remove(i);
		    						
		    					} 
		    				}
		    			}
		    		}
		    			
		     	} 
				
			 	contador++;
			}
			
	    	
	    	
	    	return salida; 
	    }
		
		private void buscarCaminosAux(Vertice v1,Vertice v2, List<Vertice> visitados, List<List<Vertice>> todos) {
		    List<Vertice> adyacentes = this.getAdyacente(v1);
		    List<Vertice> copiaVisitados =null;
		    	
		   	 for(Vertice ady: adyacentes){
		    		 
		    	 copiaVisitados = visitados.stream().collect(Collectors.toList());
		    		 
		    	if(ady.getEst().getId().equals(v2.getEst().getId())) {
		    		if(!ady.getEst().getEstado().equals("En mantenimiento")) {
		    			copiaVisitados.add(ady);
			    		todos.add(new ArrayList<Vertice>(copiaVisitados));
		    		}
		    		 
		    	} else {
		    		if( !copiaVisitados.contains(ady)) {
		    			if(!ady.getEst().getEstado().equals("En mantenimiento")) {
		    				copiaVisitados.add(ady);
		    				this.buscarCaminosAux(ady,v2,copiaVisitados,todos);
		    			}
		    		}
		   		}
		   	 }
		   }
	    
	    protected Arista buscarArista(Estacion v1, Estacion v2){
	    	return this.buscarArista(new Vertice(v1), new Vertice(v2));
	    }

	   
	    protected Arista buscarArista(Vertice v1, Vertice v2){
	    	for(Arista unaArista : this.aristas) {
	    		
	    		if(unaArista.getOrigen().getEst().getId().equals(v1.getEst().getId()) && unaArista.getFin().getEst().getId().equals(v2.getEst().getId())) return unaArista;
	    	}
	    	return null;
	    }
	    
	    
	    public Map<Vertice, Integer> caminosMinimoDikstraDistancia(Vertice origen) {
	    	
	    	// inicializo todas las distancias a INFINITO
	    	Map<Vertice, Integer> distancias = new HashMap<Vertice, Integer>();
	    	for(Vertice unVertice : this.vertices) {
	    		distancias.put(unVertice, Integer.MAX_VALUE);
	    	}
	    	distancias.put(origen, 0);
	    	
	    	// guardo visitados y pendientes de visitar
	    	Set<Vertice> visitados = new HashSet<Vertice>();
	    	TreeMap<Integer,Vertice> aVisitar= new TreeMap<Integer,Vertice>();

	    	aVisitar.put(0,origen);
	    	 
	    	while (!aVisitar.isEmpty()) {
	    		Entry<Integer, Vertice> nodo = aVisitar.pollFirstEntry();
	    		visitados.add(nodo.getValue());
	    		
	        	int nuevaDistancia = Integer.MIN_VALUE;
	        	
	        	List<Vertice> adyacentes = this.getAdyacente(nodo.getValue());
	        	
	        	for(Vertice unAdy : adyacentes) {
	        		if(!visitados.contains(unAdy)) {
	        			Arista enlace = this.buscarArista(nodo.getValue(), unAdy);
	        			if(enlace !=null) {
	        				nuevaDistancia = enlace.getDuracionViaje().intValue();
	        			}
	        			int distanciaHastaAdy = distancias.get(nodo.getValue()).intValue();
	        			int distanciaAnterior = distancias.get(unAdy).intValue();
	        			
	        			if(distanciaHastaAdy  + nuevaDistancia < distanciaAnterior ) {
	        				distancias.put(unAdy, distanciaHastaAdy  + nuevaDistancia);
	        				aVisitar.put(distanciaHastaAdy  + nuevaDistancia,unAdy);
	        				
	        			}        			
	        		}
	        	}    		
	    	}
	    	return distancias;
	    }
	    
	    
	    public Map<Vertice, Integer> caminosMinimoDikstraDuracion(Vertice origen) {

	    	// inicializo todas las distancias a INFINITO
	    	Map<Vertice, Integer> tiempo = new HashMap<Vertice, Integer>();
	    	for(Vertice unVertice : this.vertices) {
	    		tiempo.put(unVertice, Integer.MAX_VALUE);
	    	}
	    	tiempo.put(origen, 0);
	    	
	    	// guardo visitados y pendientes de visitar
	    	Set<Vertice> visitados = new HashSet<Vertice>();
	    	TreeMap<Integer,Vertice> aVisitar= new TreeMap<Integer,Vertice>();

	    	aVisitar.put(0,origen);
	    	 
	    	while (!aVisitar.isEmpty()) {
	    		Entry<Integer, Vertice> nodo = aVisitar.pollFirstEntry();
	    		visitados.add(nodo.getValue());
	    		
	        	
	    		int nuevaDuracion = Integer.MIN_VALUE;
	        	
	        	
	        	List<Vertice> adyacentes = this.getAdyacente(nodo.getValue());
	        	
	        	for(Vertice unAdy : adyacentes) {
	        		if(!visitados.contains(unAdy)) {
	        			Arista enlace = this.buscarArista(nodo.getValue(), unAdy);
	        			if(enlace !=null) {
	        				nuevaDuracion = enlace.getDuracionViaje().intValue();

	        			}
	        			int duracionHastaAdy = tiempo.get(nodo.getValue()).intValue();
	        			int duracionAnterior = tiempo.get(unAdy).intValue();
	        			
	        			if(duracionHastaAdy  + nuevaDuracion < duracionAnterior ) {
	        				tiempo.put(unAdy, duracionHastaAdy  + nuevaDuracion);
	        				aVisitar.put(duracionHastaAdy  + nuevaDuracion,unAdy);
	        				
	        			}        			
	        		}
	        	}    		
	    	}
//	    	System.out.println("TIEMPO DESDE "+origen);
//	    	System.out.println("Resultado: "+tiempo);
	    	return tiempo;
	    }
	    
	    
	    public int[][] matrizAdyacenciaNodos(){
	    	
	    	int cantVertices= this.vertices.size();
	    	int[][] matriz = new int[cantVertices][cantVertices];
	    	
	    	for(int i=0; i<cantVertices;i++) {
	        	for(int j=0; j<cantVertices;j++) {
	        		if(i== j) {
	        			matriz[i][j] = 0;        			
	        		}else {
		        		Arista arista = this.buscarArista(this.vertices.get(i), this.vertices.get(j));
		        		if(arista!=null) {
		        			matriz[i][j] = 1;        			
		        		} else {
		        			matriz[i][j] = 0;        			
		        		}
	        		}
	        	}    		
	    	}
	    	
	    	return matriz;
	    }
	    
	    
	    public void floydWarshallDistancia() {
	    	int cantVertexs= this.vertices.size();
	    	int[][] matrizDistancias = new int[cantVertexs][cantVertexs];
	    	
	    	for(int i=0; i<cantVertexs;i++) {
	        	for(int j=0; j<cantVertexs;j++) {
	        		if(i== j) {
	            		matrizDistancias[i][j] = 0;        			
	        		}else {
		        		Arista arista = this.buscarArista(this.vertices.get(i), this.vertices.get(j));
		        		if(arista!=null) {
		            		matrizDistancias[i][j] = arista.getDistancia().intValue();       			
		        		} else {
		            		matrizDistancias[i][j] = 9999;        			
		        		}
	        		}
	        	}    		
	    	}
	    	printMatrix(matrizDistancias);
	    	
	    	for (int k = 0; k < cantVertexs; k++) 
	        { 
	            // Pick all vertices as source one by one 
	            for (int i = 0; i < cantVertexs; i++) 
	            { 
	                // Pick all vertices as destination for the 
	                // above picked source 
	                for (int j = 0; j < cantVertexs; j++) 
	                { 
	                    // If vertex k is on the shortest path from 
	                    // i to j, then update the value of dist[i][j] 
	                    if (matrizDistancias[i][k] + matrizDistancias[k][j] < matrizDistancias[i][j]) 
	                    	matrizDistancias[i][j] = matrizDistancias[i][k] + matrizDistancias[k][j]; 
	                } 
	            } 
	            System.out.println("MATRIZ "+k);
	            printMatrix(matrizDistancias);
	        } 
	    	
	    }
	    
	    public void printMatrix(int[][] m) {
	    	for(int i=0; i<m.length;i++) {
	    		System.out.print("[ ");
	        	for(int j=0; j<m[i].length;j++) {
	        		System.out.print(i+":"+j+" = "+m[i][j]+ ",   ");
	        	}
	        	System.out.println(" ]");
	    	} 
	    }
	    
	    public int[][] floydWarshallDuracion() {
	    	int cantVertices= this.vertices.size();
	    	int[][] matrizDuracion = new int[cantVertices][cantVertices];
	    	
	    	for(int i=0; i<cantVertices;i++) {
	        	for(int j=0; j<cantVertices;j++) {
	        		if(i== j) {
	            		matrizDuracion[i][j] = 0;        			
	        		}else {
		        		Arista arista = this.buscarArista(this.vertices.get(i), this.vertices.get(j));
		        		if(arista!=null) {
		            		matrizDuracion[i][j] = arista.getDuracionViaje().intValue();        			
		        		} else {
		            		matrizDuracion[i][j] = 9999;        			
		        		}
	        		}
	        	}    		
	    	}
	    	//imprimirMatriz(matrizDuracion); Matriz inicial
	    	
	    	for (int k = 0; k < cantVertices; k++) { 
	            
	            for (int i = 0; i < cantVertices; i++)  { 
	             
	                for (int j = 0; j < cantVertices; j++)  { 
	                    
	                    if (matrizDuracion[i][k] + matrizDuracion[k][j] < matrizDuracion[i][j]) 
	                    	matrizDuracion[i][j] = matrizDuracion[i][k] + matrizDuracion[k][j]; 
	                } 
	            } 
	            
	        } 
	    	
	    	for (int i = 0; i < cantVertices; i++)  { 
	            for (int j = 0; j < cantVertices; j++)   { 
	                if (matrizDuracion[i][j] == 9999) 
	                	matrizDuracion[i][j] = 000; 
	            } 
	        }
	    	return matrizDuracion;
	    }
	    
	    
	    public int[][] floydWarshallCosto() {
	    	int cantVertices= this.vertices.size();
	    	int[][] matrizCosto = new int[cantVertices][cantVertices];
	    	
	    	for(int i=0; i<cantVertices;i++) {
	        	for(int j=0; j<cantVertices;j++) {
	        		if(i== j) {
	        			matrizCosto[i][j] = 0;        			
	        		}else {
		        		Arista arista = this.buscarArista(this.vertices.get(i), this.vertices.get(j));
		        		if(arista!=null) {
		        			matrizCosto[i][j] = arista.getCosto().intValue();        			
		        		} else {
		        			matrizCosto[i][j] = 9999;        			
		        		}
	        		}
	        	}    		
	    	} 
	    	
	    	for (int i = 0; i < cantVertices; i++)  { 
	         
	            for (int j = 0; j < cantVertices; j++)   { 
	                
	                if (matrizCosto[i][j] == 9999) 
	                	matrizCosto[i][j] = 000; 
	            } 
	        }
	    	return matrizCosto;
	    }
	    
	    
	    public int[] obtenerFilaMatriz(int fila, int[][] matriz) {
	    	
	    	int[] arreglo = new int [matriz.length];
	    	for(int i = 0; i< matriz.length; i++) {
	    		arreglo[i]=matriz[fila][i];    		
	    	}
	    	
	    	return arreglo;
	    }
	    
	    
	    public double[] rankPages() {
	        
	        double pagerank[] = new double[this.vertices.size()];
	        double InitialPageRank = 0.0;
	        double OutgoingLinks = 0;
	        double DampingFactor = 0.85;
	        double TempPageRank[] = new double[100];
	        int ExternalNodeNumber;
	        int InternalNodeNumber;
	        int k = 1; // For Traversing
	        int ITERATION_STEP = 1;
	        
	      	InitialPageRank =  (1.0 / (double) this.vertices.size());
	      	  
	        int[][] path = this.matrizAdyacenciaNodos();
	        
	        for (k = 0; k < this.vertices.size(); k++) {
	       	   pagerank[k] = InitialPageRank;
	        }

	       	  while (ITERATION_STEP <= 2) // Iterations
	       	  {
	       	   // Store the PageRank for All Nodes in Temporary Array 
	       	   for (k = 0; k < this.vertices.size(); k++) {
	       	    TempPageRank[k] = pagerank[k];
	       	    pagerank[k] = 0;
	       	   }

	       	   for (InternalNodeNumber = 0; InternalNodeNumber < this.vertices.size(); InternalNodeNumber++) {
	       		   for (ExternalNodeNumber = 0; ExternalNodeNumber < this.vertices.size(); ExternalNodeNumber++) {
	       			   if (path[ExternalNodeNumber][InternalNodeNumber] == 1) {
	       				   k = 0;
			       	       OutgoingLinks = 0.0; // Count the Number of Outgoing Links for each ExternalNodeNumber
			       	       while (k < this.vertices.size()) {
				       	       if (path[ExternalNodeNumber][k] == 1) {
				       	    	   OutgoingLinks = OutgoingLinks + 1.0; // Counter for Outgoing Links
				       	       }
				       	       k = k + 1;
			       	      }
			       	      // Calculate PageRank     
			       	      pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber] * (1.0 / (double) OutgoingLinks);
			       	     }
			       	    }
			       	   }

	       	   ITERATION_STEP = ITERATION_STEP + 1;
	       	  }
	       	  // Add the Damping Factor to PageRank
	       	  for (k = 0; k < this.vertices.size(); k++) {
	       		  pagerank[k] = (1 - DampingFactor) + DampingFactor * pagerank[k];
	       	  }
	       	  
	       	  return pagerank;
	   }
	    
	    
	    public boolean bfs(int rGraph[][], int s, int t, int parent[]) { 
	    	int V = rGraph.length;
	        boolean visited[] = new boolean[V]; 
	        for(int i=0; i<V; ++i) 
	            visited[i]=false; 
	  
	        // Create a queue, enqueue source vertex and mark 
	        // source vertex as visited 
	        LinkedList<Integer> queue = new LinkedList<Integer>(); 
	        queue.add(s); 
	        visited[s] = true; 
	        parent[s]=-1; 
	  
	        // Standard BFS Loop 
	        while (queue.size()!=0) 
	        { 
	            int u = queue.poll(); 
	  
	            for (int v=0; v<V; v++) 
	            { 
	                if (visited[v]==false && rGraph[u][v] > 0) 
	                { 
	                    queue.add(v); 
	                    parent[v] = u; 
	                    visited[v] = true; 
	                } 
	            } 
	        } 
	  
	        // If we reached sink in BFS starting from source, then 
	        // return true, else false 
	        return (visited[t] == true); 
	    }
	    
	    
	    public int flujoMaximo(int graph[][], int s, int t) { 
	    	int V = graph.length;
	        int u, v; 
	  
	        // Create a residual graph and fill the residual graph 
	        // with given capacities in the original graph as 
	        // residual capacities in residual graph 
	  
	        // Residual graph where rGraph[i][j] indicates 
	        // residual capacity of edge from i to j (if there 
	        // is an edge. If rGraph[i][j] is 0, then there is 
	        // not) 
	        int rGraph[][] = new int[V][V]; 
	        
	        for (u = 0; u < V; u++) 
	            for (v = 0; v < V; v++) 
	                rGraph[u][v] = graph[u][v]; 
	  
	        // This array is filled by BFS and to store path 
	        int parent[] = new int[V]; 
	  
	        int max_flow = 0;  // There is no flow initially 
	  
	        // Augment the flow while tere is path from source 
	        // to sink 
	        while (bfs(rGraph, s, t, parent))  { 
	            // Find minimum residual capacity of the edhes 
	            // along the path filled by BFS. Or we can say 
	            // find the maximum flow through the path found. 
	            int path_flow = Integer.MAX_VALUE; 
	            for (v=t; v!=s; v=parent[v]) 
	            { 
	                u = parent[v]; 
	                path_flow = Math.min(path_flow, rGraph[u][v]); 
	            } 
	  
	            // update residual capacities of the edges and 
	            // reverse edges along the path 
	            for (v=t; v != s; v=parent[v]) 
	            { 
	                u = parent[v]; 
	                rGraph[u][v] -= path_flow; 
	                rGraph[v][u] += path_flow; 
	            } 
	  
	            // Add path flow to overall flow 
	            max_flow += path_flow; 
	        } 
	  
	        // Return the overall flow 
	        return max_flow; 
	    }
	    
	    
	    
	    

}
