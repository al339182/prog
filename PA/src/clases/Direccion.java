package clases;

import java.io.Serializable;

public class Direccion implements Serializable{
	
	private static final long serialVersionUID = -8586548726005993160L;
	private int codigoPostal;
	private String provincia;
	private String poblacion;
	
	public Direccion(int codigo , String prov , String pobl){
		this.codigoPostal=codigo;
		this.provincia=prov;
		this.poblacion=pobl;
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("Código Postal: "+codigoPostal+"\n");
		sb.append("Provincia: "+provincia+"\n");
		sb.append("Población: "+poblacion+"\n");
		return sb.toString();
	}
}
