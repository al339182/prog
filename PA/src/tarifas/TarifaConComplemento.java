package tarifas;

import clases.Llamada;

public class TarifaConComplemento extends Tarifa {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5782752842656709845L;
	private Tarifa tarifa;
	
	public TarifaConComplemento(Tarifa tarifa,float precio){
		super(precio);
		this.tarifa=tarifa;
	}

	@Override
	public float calcularCoste(Llamada llamada) {
		return tarifa.calcularCoste(llamada);
	}
	@Override
	public String toString(){
		return tarifa.toString();
	}
	
}
