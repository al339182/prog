package clases;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import excepciones.PeriodoNoValidoExeption;
import interfaces.Temporales;
import tarifas.ConOferta;

public class CompanyiaTelefonica implements Serializable {
	
	private static final long serialVersionUID = -8202818482257465497L;
	private HashMap<String,Cliente> clientes;
	private  int idFacturaActual;
	public CompanyiaTelefonica(){
		clientes=new HashMap<String,Cliente>();
		idFacturaActual=1;
	}
	public  void addCliente(Cliente cliente) {
		clientes.put(cliente.getNif(),cliente);
		}
	public void borrarCliente(String DNI){
			clientes.remove(DNI);
	}
	public void cambiarTarifa(String NIF,ConOferta tarifaNueva){
			getCliente(NIF).setTarifa(tarifaNueva);
	}
	public String datosClienteString(String nif){
		return getCliente(nif).toString();
	}
	public static int sumaDuraciones(ArrayList<Llamada> listaLlamadas){
		int suma=0;
		for(Llamada llamada:listaLlamadas){
			suma+=llamada.getDuracion();
		}
		return suma;
	}
	public String perteneceA(Factura factura){
		return factura.getNifFactura();
	}
	public void addFactura(String dni,Factura factura){
		Cliente cliente=this.getCliente(dni);
		cliente.addFactura(factura);
	}
	public ArrayList<Cliente> listaClientes(){//devuelve la lista de clientes(vacía si no hay ningun cliente)
		ArrayList<Cliente> listaClientes=new ArrayList<Cliente>();
		for(String dni:clientes.keySet()){
			listaClientes.add(this.getCliente(dni));
		}
		return listaClientes;
	}
	public Cliente getCliente(String nif){//devuelve el objeto cliente
		return clientes.get(nif);
	}
	public void addLlamada(String DNI,Llamada llamada){
		getCliente(DNI).addLlamada(llamada);
	}	
	public  ArrayList<Llamada> listaLlamadasCliente(String DNI){
		return getCliente(DNI).getListaLlamadas();
	}
	public Factura crearFactura(String DNI,LocalDate fechaFactura,LocalDate fechaInicio,LocalDate fechaFin) throws PeriodoNoValidoExeption{
		ArrayList<Llamada> llamadasFactura=getCliente(DNI).getllamadasEntre(fechaInicio, fechaFin);
		int id=idFacturaActual;
		idFacturaActual++;
		return new Factura(DNI,id,clientes.get(DNI).getTarifa(),fechaFactura,new Periodo(fechaInicio,fechaFin),sumaDuraciones(llamadasFactura),llamadasFactura);
	}
	public Factura getFactura(int codigoId){//devuelve null si no está
		Iterator<Cliente> it=clientes.values().iterator();
		while(it.hasNext()){
			Cliente actual=it.next();
			for(Factura factura:getFacturasCliente(actual.getNif())){
				if(factura.getId()==codigoId)
					return factura;
			}
		}
		return null;
		
	}
	public <T extends Temporales> ArrayList<T> entreFechas(ArrayList<T> lista ,Periodo periodo){
		ArrayList<T> listaReturn=new ArrayList<T>();
		for(T objetoConFecha:lista){
			LocalDate fechaCandidato=objetoConFecha.getFecha();
			if(fechaCandidato.compareTo(periodo.getInicio())>=0 && fechaCandidato.compareTo(periodo.getFin())<=0)
				listaReturn.add(objetoConFecha);
		}		
		return listaReturn;
	}
	public boolean contains(String nif){
		if(clientes.containsKey(nif))
			return true;
		return false;
		
	}
	public ArrayList<Factura> getFacturasCliente(String nif){ 
		return getCliente(nif).getListaFacturas();
	}
	public boolean borrarLlamada(String nif,int id){//devuelve fasle si la llamada no está en la lista
		return getCliente(nif).borrarLlamada(id);
	}

}