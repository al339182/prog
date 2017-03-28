package excepciones;

import java.time.LocalDate;

public class PeriodoNoValidoExeption extends Exception{
	
	private static final long serialVersionUID = 7141206520589729382L;
	public PeriodoNoValidoExeption(){
		super("El periodo indicado no es válido");
	}
	public PeriodoNoValidoExeption(String mensaje){
		super(mensaje);
	}
	public PeriodoNoValidoExeption(LocalDate fecha1,LocalDate fecha2){
		super("El periodo indicado por "+fecha1.toString()+"y "+fecha2.toString()+ " no es válido");
	}
}
