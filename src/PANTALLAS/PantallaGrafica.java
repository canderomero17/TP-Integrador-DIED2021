package PANTALLAS;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import EstructuraGRAFO.Grafo;
import EstructuraGRAFO.Vertice;
import Objetos.Estacion;
import graficoNodo.Dibujo;

@SuppressWarnings("serial")
public class PantallaGrafica extends JDialog {
	
	public PantallaGrafica(String origen, String destino) {
		setBackground(new Color(255, 255, 204));
		Grafo grafo = new Grafo();
		grafo.armarGrafoEstacion();
		
		Color colorFinal = null;
	
		Dibujo dibujo = new Dibujo(this);
		setContentPane(dibujo);
	
		Estacion nuevaEstacionOrigen = new Estacion(origen);
		Estacion nuevaEstacionDestino = new Estacion(destino);
		
		List<List<Vertice>> listaCaminos = new ArrayList<List<Vertice>>();
		listaCaminos = grafo.caminos(nuevaEstacionOrigen, nuevaEstacionDestino);
		List<String> caminosMostrar = new ArrayList<String>();
		
		for(int i=0; i<listaCaminos.size(); i++) {
			for(int j=0; j<listaCaminos.get(i).size(); j++) {
				caminosMostrar.add(listaCaminos.get(i).get(j).getEst().getId());
				}
		}
		caminosMostrar = caminosMostrar.stream().distinct().collect(Collectors.toList());
		
		for(int t=0; t<caminosMostrar.size(); t++) {
			dibujo.mostrarCirculo(caminosMostrar.get(t));
		}
			
		for(int j=0; j<grafo.getAristas().size(); j++) {
			String idOrigen = grafo.getAristas().get(j).getOrigen().getEst().getId();
			String idDestino = grafo.getAristas().get(j).getFin().getEst().getId();
			String color = "";
			
			for(int i=0; i<caminosMostrar.size(); i++) {
				for(int t=0; t<caminosMostrar.size(); t++) {
					if(idOrigen.equals(caminosMostrar.get(i)) && idDestino.equals(caminosMostrar.get(t))) {
						if(grafo.getTrayectoColor().get(j).getOrigen().getId().equals(idOrigen)) {
							if(grafo.getTrayectoColor().get(j).getDestino().getId().equals(idDestino)) {
								color = grafo.getTrayectoColor().get(j).getColorLinea();
							}
						}
						if(color.equals("Verde Claro")) colorFinal = Color.GREEN;
						if(color.equals("Rojo")) colorFinal = Color.RED;
						if(color.equals("Amarillo")) colorFinal = Color.YELLOW;
						if(color.equals("Verde Oscuro")) colorFinal = Color.BLACK;
						if(color.equals("Celeste")) colorFinal = Color.CYAN;
						if(color.equals("Naranja")) colorFinal = Color.ORANGE;
						dibujo.conectarCirculos(idOrigen, idDestino, colorFinal);
					}
				}
			}
		}
	}

}
