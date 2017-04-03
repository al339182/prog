package tarifas;

import clases.Llamada;

public abstract class Tarifa {
	private float precio;
	
	public Tarifa(float precio){
		this.precio=precio;
	}
	
	public float getPrecio(){
		return precio;
	}
	public abstract float calcularCoste(Llamada llamada);
	
}
