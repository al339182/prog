package tarifas;

import java.time.DayOfWeek;

import clases.Llamada;

public class TarifaDiaSemana extends ConOferta {
	/**
	 * 
	 */
	private static final float TARIFA_GRATIS=0f;
	private static final long serialVersionUID = -773738253931725126L;
	private int dia;
	public TarifaDiaSemana(Tarifa tarifa,int dia){
		super(tarifa,TARIFA_GRATIS);
		this.dia=dia;
		
	}

	@Override
	public float calcularCoste(Llamada llamada) {
		if(llamada.getFecha().getDayOfWeek()==DayOfWeek.of(dia)){
			return Math.min(getPrecio(), super.getPrecio());
		}else{
			return super.getPrecio();
		}
		
	}
}
