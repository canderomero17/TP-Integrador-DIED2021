package Objetos;

import java.time.LocalDateTime;

public class Boleto {
	
	private Integer nroBoleto;
	private String correoCliente;
	private String nombreCliente;
	private LocalDateTime fecha;
	private String estacionOrigen;
	private String estacionDestino;
	private String caminoASeguir;
	private Double costo;
	
	
	public Boleto(Integer nroBoleto, String nombre, String correo, LocalDateTime fecha, String estacionOrigen,
			String estacionDestino, String caminoASeguir, Double costo) {
		
		this.nroBoleto = nroBoleto;
		this.nombreCliente = nombre;
		this.correoCliente = correo;
		this.fecha = fecha;
		this.estacionOrigen = estacionOrigen;
		this.estacionDestino = estacionDestino;
		this.caminoASeguir = caminoASeguir;
		this.costo = costo;
	}


	public Integer getNroBoleto() {
		return nroBoleto;
	}


	public void setNroBoleto(Integer nroBoleto) {
		this.nroBoleto = nroBoleto;
	}


	public String getCorreoCliente() {
		return correoCliente;
	}


	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public LocalDateTime getFecha() {
		return fecha;
	}


	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


	public String getEstacionOrigen() {
		return estacionOrigen;
	}


	public void setEstacionOrigen(String estacionOrigen) {
		this.estacionOrigen = estacionOrigen;
	}


	public String getEstacionDestino() {
		return estacionDestino;
	}


	public void setEstacionDestino(String estacionDestino) {
		this.estacionDestino = estacionDestino;
	}


	public String getCaminoASeguir() {
		return caminoASeguir;
	}


	public void setCaminoASeguir(String caminoASeguir) {
		this.caminoASeguir = caminoASeguir;
	}


	public Double getCosto() {
		return costo;
	}


	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	
	
	

}
