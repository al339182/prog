package fabricas;

import java.time.LocalDate;

import clases.Direccion;
import clases.Empresa;
import clases.Particular;
import tarifas.Tarifa;

public class FabricaClientes {

		public Empresa getEmpresa(String name,String nif,String eMail,LocalDate fechaAlta,Direccion direccionCliente,Tarifa tarifaCl){
			return new Empresa(name,nif,eMail,fechaAlta,direccionCliente,tarifaCl);
		}
		public Particular getParticular(String name,String nif,String eMail,LocalDate fechaAlta,Direccion direccionCliente,Tarifa tarifaCl,String apellidos){
			return new Particular(name, nif, eMail, fechaAlta, direccionCliente, tarifaCl, apellidos);
		}
}
