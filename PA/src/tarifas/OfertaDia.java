package tarifas;

import tarifas.Tarifa;

import java.time.DayOfWeek;
import clases.Llamada;

public class OfertaDia extends TarifaConComplemento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4167626174664681589L;
	private DayOfWeek diaDeOferta;
	
	public OfertaDia (Tarifa tarifa, float precioOfertaDia, DayOfWeek diaDeOferta) {
		super(tarifa, precioOfertaDia);
		this.diaDeOferta = diaDeOferta;
	}
	@Override
	public float calcularCoste (Llamada llamada){
		
		if(llamada.getFecha().getDayOfWeek() == diaDeOferta){
			return 0;
		}else{
			return super.calcularCoste(llamada);
		}
		
	}
	@Override
	public String toString(){
		return super.toString()+" y gratis si el día de la semana es "+diaDeOferta.toString();
	}
	
	
}
