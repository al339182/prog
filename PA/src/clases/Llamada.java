package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import interfaces.Temporales;

public class Llamada implements Temporales,Serializable{
	
	private static final long serialVersionUID = -1024019363912753872L;
	private static int idLlamada=1;
	private String telefonoDestino;
	private LocalDate fechaLlamada;
	private LocalTime hora;
	private int duracion;
	private int id;
	
	public Llamada( String tlfDest, LocalDate fechLlam, LocalTime horaLLam, int dura){
		this.telefonoDestino=tlfDest;
		this.fechaLlamada = fechLlam;
		this.hora= horaLLam;
		this.duracion=dura;
		this.id=idLlamada;
		idLlamada++;
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append(telefonoDestino+"-");
		sb.append(fechaLlamada.toString()+"-");
		sb.append(hora.toString()+"-");
		sb.append(duracion);
		return sb.toString();
	}
	public int getDuracion(){
		return duracion;
	}
	public int getId(){
		return id;
	}
	@Override
	public LocalDate getFecha() {
		return fechaLlamada;
	}
	public LocalTime getHora(){
		return hora;
	}
}