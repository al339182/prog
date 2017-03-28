package tarifas;

import java.io.Serializable;

import clases.Llamada;

public abstract class Tarifa implements Serializable {
	
	private static final long serialVersionUID = 2897797030586242172L;
	private static final float TARIFA_BASICA=0.15f;
	private float importe;
	
	public Tarifa(float precio){
		importe=precio;
	}
	public float getPrecio(){
		return importe;
	}
	public abstract float calcularCoste(Llamada llamada);
	
}
