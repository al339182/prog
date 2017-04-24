package clases;

import java.time.LocalDate;

import tarifas.Tarifa;

public class Particular extends Cliente {


	private static final long serialVersionUID = 8779859492537368444L;
	private String apellidos;
	
	public Particular(String name,String nif,String eMail,LocalDate fechaAlta,Direccion dir,Tarifa tar,String apellidos){
		super(name,nif,eMail,fechaAlta,dir,tar);
		this.apellidos=apellidos;
	}
	public String getApellidos(){
		return apellidos;
	}
	@Override
	public String toString(){
			StringBuilder sb=new StringBuilder();
			sb.append("Tipo de cliente: Particular"+"\n");
			sb.append("Nombre: "+getNombre()+"\n");
			sb.append("Apellidos: "+getApellidos()+"\n");
			sb.append("NIF: "+getNif()+"\n");
			sb.append("eMail: "+getEmail()+"\n");
			sb.append(getDireccion().toString());
			sb.append("Fecha de alta: "+getFecha().toString()+"\n");
			sb.append("Tarifa actual: "+getTarifa().toString()+"\n");
			return sb.toString();
	}
}

