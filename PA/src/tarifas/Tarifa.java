package tarifas;

import java.io.Serializable;

import clases.Llamada;

public abstract class Tarifa implements Serializable{
	private float precio;
	
	public Tarifa(float precio){
		this.precio=precio;
	}
	
	public float getPrecio(){
		return precio;
	}
	public abstract float calcularCoste(Llamada llamada);
	
}
