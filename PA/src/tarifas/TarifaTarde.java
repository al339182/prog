package tarifas;

import java.time.LocalTime;

import clases.Llamada;

public class TarifaTarde extends ConOferta {

	private static final long serialVersionUID = 3876078938835476497L;

	public TarifaTarde(Tarifa tarifa, float precio) {
		super(tarifa, precio);
	}
	@Override
	public float calcularCoste(Llamada llamada){
		if(llamada.getHora().compareTo(LocalTime.of(16,0))>=0 && llamada.getHora().compareTo(LocalTime.of(0,0))<=0){
			return Math.min(getPrecio(),super.getPrecio());
		}else{
			return super.getPrecio();
		}
	}

}
