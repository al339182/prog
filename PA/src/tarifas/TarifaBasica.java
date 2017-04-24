package tarifas;

import java.io.Serializable;

import clases.Llamada;

public class TarifaBasica extends Tarifa implements Serializable{
	
	public static final float PRECIO_BASICO=0.15f;
	
	public TarifaBasica(){
		super(PRECIO_BASICO);
	}
	
	@Override
	public float calcularCoste(Llamada llamada) {
		return llamada.getDuracion()*super.getPrecio()/60;
	}
	@Override
	public String toString(){
		return "La tarifa es de "+PRECIO_BASICO+ " por minuto";
	}
}
