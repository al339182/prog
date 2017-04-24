package tarifas;

import tarifas.Tarifa;
import clases.Llamada;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

import interfaces.Temporales;

public class OfertaHoras extends TarifaConComplemento {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8798198436506610672L;

	public static final float precioOfertaHoras = 0.05f;
	
	public LocalTime franjaInf;
	public LocalTime franjaSup;
	
	
	public OfertaHoras(Tarifa tarifa, float precioOfertaHoras, int franja){//0 si es mañana,1 si es tarde, 2 si es noche
		super(tarifa, precioOfertaHoras);
		
		
	    
		switch(franja){
			case 0:
				franjaInf = LocalTime.of(0, 0);
				franjaSup = LocalTime.of(8, 0);
				break;
			case 1:
				franjaInf= LocalTime.of(8, 0);
				franjaSup= LocalTime.of(16, 0);
				break;
			case 2:
				franjaInf= LocalTime.of(16, 0);
				franjaSup= LocalTime.of(0, 0);
				break;
		}
	}
	@Override
	public float calcularCoste  (Llamada llamada){
		if(llamada.getHora().compareTo(franjaInf)>=0 && llamada.getHora().compareTo(franjaSup)<=0 ){
			return Math.min(super.calcularCoste(llamada),llamada.getDuracion()*precioOfertaHoras);
		}else{
			return super.calcularCoste(llamada);
		}
	}
	public String toString(){
		return super.toString()+" y en la franja desde las "+franjaInf.toString()+" hasta las "+franjaSup.toString()+" cuesta "+precioOfertaHoras;
	}
	

}
