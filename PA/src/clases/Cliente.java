package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import interfaces.Temporales;
import tarifas.ConOferta;

public abstract class Cliente implements Serializable,Temporales {
	private static final long serialVersionUID = -980571864724160741L;
	private String name;
	private String NIF;
	private String eMail;
	private LocalDate fechaAlta;
	private Direccion direccion;
	private ArrayList<Llamada> llamadasCliente;
	private ConOferta tarifaCl; //tarifa que se le aplic al cliente , se modificarÃ  en siguientes practicas
	private HashMap<Integer,Factura> facturas; // para organizar las facturas por codigo usamos un map
	
	public Cliente (String name,String nif,String eMail,LocalDate fechaAlta,Direccion dir,ConOferta tar){
		this.name=name;
		NIF=nif;
		this.eMail=eMail;
		this.fechaAlta=fechaAlta;
		this.direccion=dir;
		tarifaCl=tar;
		this.llamadasCliente=new ArrayList<Llamada>();
		this.facturas= new HashMap<Integer,Factura>();
	}
	public String getNombre(){
		return name;
	}
	public LocalDate getFecha() {
		return fechaAlta;
	}
	public String getNif() {
		return this.NIF;
	}
	public ConOferta getTarifa() {
		return tarifaCl;
	}
	public ArrayList<Llamada> getllamadasEntre(LocalDate fechaInicio, LocalDate fechaFin) {
		ArrayList<Llamada> listaLlamadas=new ArrayList<Llamada>();
		Iterator<Llamada> it=llamadasCliente.iterator();
		while(it.hasNext()){
			Llamada actual=it.next();
			if(actual.getFecha().compareTo(fechaInicio)>=0||actual.getFecha().compareTo(fechaFin)<=0)//si está entre las fechas
				listaLlamadas.add(actual);
		}
		return listaLlamadas;
	}
	public ArrayList<Factura> getListaFacturas() {
		ArrayList<Factura> retVal=new ArrayList<Factura>();
		retVal.addAll(facturas.values());
		return retVal;
	}
	public void addLlamada(Llamada llamada) {
		llamadasCliente.add(llamada);	
	}
	public void setTarifa(ConOferta tarifa) {
		this.tarifaCl=tarifa;
	}
	public ArrayList<Llamada> getListaLlamadas() {
		return llamadasCliente;
	}
	public Direccion getDireccion() {
		return this.direccion;
	}
	public String getEmail() {
		return this.eMail;
	}
	public void setEmail(String nuevoEmail) {
		this.eMail=nuevoEmail;
	}
	public void addFactura(Factura factura) {
		facturas.put(factura.getId(),factura);
	}
	public void setFecha(LocalDate fechaNueva){
		fechaAlta=fechaNueva;
	}
	public boolean borrarLlamada(int id){
		for(Llamada llamada:llamadasCliente){
			if(llamada.getId()==id){
				llamadasCliente.remove(llamada);
				return true;
			}
		}
		return false;
	}
}