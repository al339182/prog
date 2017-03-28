package clases;

import java.io.Serializable;
import java.time.LocalDate;

import excepciones.PeriodoNoValidoExeption;

public class Periodo implements Serializable {
	
	private static final long serialVersionUID = 1286633453229945743L;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public Periodo(LocalDate fechaInicio,LocalDate fechaFin) throws PeriodoNoValidoExeption{
		if(fechaInicio.compareTo(fechaFin)>=0)
			throw new PeriodoNoValidoExeption(fechaInicio,fechaFin);
		this.fechaInicio=fechaInicio;
		this.fechaFin=fechaFin;
	}
	public LocalDate getInicio(){
		return fechaInicio;
	}
	public LocalDate getFin(){
		return fechaFin;
	}
}