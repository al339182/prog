package clases;

import java.time.LocalDate;

import tarifas.Tarifa;

public class Empresa extends Cliente {

	private static final long serialVersionUID = -5710690198633557404L;
	public Empresa(String name, String nif, String eMail, LocalDate fechaAlta, Direccion dir, Tarifa tar) {
		super(name, nif, eMail, fechaAlta, dir, tar);
		}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("Tipo de cliente: Empresa"+"\n");
		sb.append("Nombre de la empresa: "+getNombre()+"\n");
		sb.append("NIF: "+getNif()+"\n");
		sb.append("eMail: "+getEmail()+"\n");
		sb.append(getDireccion().toString());
		sb.append("Fecha de alta: "+getFecha().toString()+"\n");
		sb.append("Tarifa actual: "+getTarifa().toString()+"\n");
		return sb.toString();
	}
}
