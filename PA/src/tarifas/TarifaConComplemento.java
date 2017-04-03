package tarifas;

import clases.Llamada;

public class TarifaConComplemento extends Tarifa {
	private Tarifa tarifa;
	
	public TarifaConComplemento(Tarifa tarifa,float precio){
		super(precio);
		this.tarifa=tarifa;
	}

	@Override
	public float calcularCoste(Llamada llamada) {
		
		return Math.min(tarifa.calcularCoste(llamada),);
	}
	
}
