package tarifas;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class ConOferta extends Tarifa implements Serializable{
	
	private static final long serialVersionUID = 4610751062480979317L;
	private Tarifa tarifa;
	
	public ConOferta(Tarifa tarifa,float precio){
		super(precio);
		this.tarifa=tarifa;
	}

}
