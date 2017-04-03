package tarifas;

import clases.Llamada;

public class TarifaBasica extends Tarifa {
	public static final float PRECIO_BASICO=0.15f;
	public TarifaBasica(){
		super(PRECIO_BASICO);
	}
	@Override
	public float calcularCoste(Llamada llamada) {
		return llamada.getDuracion()*super.getPrecio();
	}
	
}
