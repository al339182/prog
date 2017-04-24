package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import interfaces.Temporales;
import tarifas.Tarifa;

public class Factura implements Temporales,Serializable {
	
	private static final long serialVersionUID = -1500734305629237654L;
	
	private String nif;
	private int codigoId;
	private Tarifa tarifa;
	private LocalDate fechaEmision;
	private Periodo periodo;
	private int duracion; //en segundos, para posteriormente aplicar la factura.
	private ArrayList<Llamada> listaLlamadasFactura;
	private float importe;
	
	public Factura(String nif,int id,Tarifa tarifa,LocalDate fechaFactura,Periodo periodo,int duracion,ArrayList<Llamada> listaLlamadas){
		this.nif=nif;
		this.codigoId=id;
		this.tarifa=tarifa;
		this.fechaEmision=fechaFactura;
		this.periodo=periodo;
		this.duracion=duracion;
		this.listaLlamadasFactura=listaLlamadas;
		float suma=0;
		for(Llamada llamada:listaLlamadas){
			suma+=tarifa.calcularCoste(llamada);
		}
		this.importe=suma;
	}
	public Factura(int id){
		this.codigoId=id;
	}
	public int getId(){
		return codigoId;
	}
	public String getNifFactura(){
		return nif;	
	}
	@Override
	public LocalDate getFecha() {
		return fechaEmision;
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("\nNIF:"+nif+"\nId:"+codigoId+"\nTarifa:"+tarifa.toString()+"\nPrecio total="+importe+"\n");
		return sb.toString();
	}
}
