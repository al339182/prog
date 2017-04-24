package clases;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import enumeraciones.OpcionesCliente;
import enumeraciones.OpcionesFacturas;
import enumeraciones.OpcionesLlamadas;
import enumeraciones.OpcionesMenu;
import enumeraciones.TipoOferta;
import excepciones.PeriodoNoValidoExeption;
import fabricas.FabricaTarifasConcr;
import tarifas.Tarifa;

public class Main {
	private static Entrada_Salida entradaSalida=new Entrada_Salida(System.in,System.out);
	public static void main(String[] args){
		CompanyiaTelefonica companyia=Main.cargarCompanyia();
		boolean salir=false;
		while(!salir){
			entradaSalida.mostrarMenu(OpcionesMenu.getMenu());
			int opcion=entradaSalida.pedirOpcion();
			while(opcion<0||opcion>OpcionesMenu.getNumeroOpciones()){
				entradaSalida.imprimir("La opción seleccionada no existe");
				opcion=entradaSalida.pedirOpcion();
			}
			OpcionesMenu opcionMenu=OpcionesMenu.getOpcion(opcion);
			switch(opcionMenu){
				case OPCIONES_CLIENTE:
					menuCliente(companyia);
					break;
				case OPCIONES_LLAMADAS:
					menuLlamadas(companyia);
					break;
				case OPCIONES_FACTURAS:
					menuFacturas(companyia);
					break;
				case GUARDAR_COMPANYIA:
					ejecutarGuardarCompanyia(companyia);
					break;
				case SALIR:
					salir=true;
					entradaSalida.imprimir("Adiós");
					break;
			default:
				break;
				}
			}
		}
	private static void menuCliente(CompanyiaTelefonica companyia){
		entradaSalida.mostrarMenu(OpcionesCliente.getMenu());
		int opcion=entradaSalida.pedirOpcion();
		OpcionesCliente opcionCliente=OpcionesCliente.getOpcion(opcion);
		while(opcion<0||opcion>OpcionesCliente.getNumeroOpciones()){
			entradaSalida.imprimir("La opción seleccionada no existe");
			opcion=entradaSalida.pedirOpcion();
		}
		switch(opcionCliente){
			case ALTA_CLIENTE:
				ejecutarDarAltaCliente(companyia);
				break;
			case BORRAR:
				ejecutarBorrarCliente(companyia);
				break;
			case CAMBIAR_TARIFA:
				ejecutarAñadirOfertaTarifa(companyia);
				break;
			case RECUPERAR_DATOS_CLIENTE:
				ejecutarRecuperarDatosCliente(companyia);
				break;
			case RECUPERAR_TODOS_CLIENTES:
				ejecutarRecuperarTodosClientes(companyia);
				break;
			case CLIENTES_ENTRE_FECHAS:
				ejecutarClientesEntreFechas(companyia);
				break;
			case SALIR:	
				break;		
		}
	}
	private static void menuLlamadas(CompanyiaTelefonica companyia){
		entradaSalida.mostrarMenu(OpcionesLlamadas.getMenu());
		int opcion=entradaSalida.pedirOpcion();
		OpcionesLlamadas opcionLlamada=OpcionesLlamadas.getOpcion(opcion);
		while(opcion<0||opcion>OpcionesLlamadas.getNumeroOpciones()){
			entradaSalida.imprimir("La opción seleccionada no existe");
			opcion=entradaSalida.pedirOpcion();
		}
		switch(opcionLlamada){
			case ALTA_LLAMADA:
				ejecutarDarAltaLlamada(companyia);
				break;
				
			case LLAMADAS_CLIENTE:
				ejecutarMostrarLlamadasCliente(companyia);
				break;
			case LLAMADAS_ENTRE_FECHAS:
				ejecutarMostrarLlamadasEntreFechas(companyia);
				break;
			case SALIR:
				break;
		}
	}
	private static void menuFacturas(CompanyiaTelefonica companyia){
		entradaSalida.mostrarMenu(OpcionesFacturas.getMenu());
		int opcion=entradaSalida.pedirOpcion();
		OpcionesFacturas opcionFacturas=OpcionesFacturas.getOpcion(opcion);
		while(opcion<0||opcion>OpcionesFacturas.getNumeroOpciones()){
			entradaSalida.imprimir("La opción seleccionada no existe");
			opcion=entradaSalida.pedirOpcion();
		}
		switch(opcionFacturas){
			case EMITIR_FACTURA:
				ejecutarEmitirFactura(companyia);
				break;
			
			case RECUPERAR_FACTURA:
				ejecutarRecuperarFactura(companyia);
				break;
			case RECUPERAR_TODAS_FACTURAS_CLIENTE:
				ejecutarRecuperarTodasFacturasCliente(companyia);
				break;
			case FACTURAS_ENTRE_FECHAS:
				ejecutarMostrarFacturasEntreFechas(companyia);
				break;
			case ATRAS:
				break;
		}
	}
	private static CompanyiaTelefonica cargarCompanyia(){
		CompanyiaTelefonica companyia;
		try {
			companyia = Guardar_Cargar.cargarCompanyia();
			entradaSalida.imprimir("Se ha cargado la compañía");
		} catch (ClassNotFoundException e) {
			entradaSalida.imprimir("Error en la lectura de datos del archivo de cargado");
			entradaSalida.imprimir("Se dispone a crear una nueva compañía");
			companyia=new CompanyiaTelefonica();
		} catch (FileNotFoundException e) {
			entradaSalida.imprimir("No existe el archivo de cargado");
			entradaSalida.imprimir("Se dispone a crear una nueva compañía");
			companyia=new CompanyiaTelefonica();
		}catch(IOException e){
			entradaSalida.imprimir("Error en la apertura del archivo de cargado");
			entradaSalida.imprimir("Se dispone a crear una nueva compañía");
			companyia=new CompanyiaTelefonica();
		}
		return companyia;
	}
	private static void ejecutarDarAltaCliente(CompanyiaTelefonica companyia){
		Cliente cliente=entradaSalida.pedirCliente();
		companyia.addCliente(cliente);
	}
	private static void ejecutarBorrarCliente(CompanyiaTelefonica companyia){
		String dni=entradaSalida.pedirDni();
		if(companyia.contains(dni)){
			companyia.borrarCliente(dni);
			entradaSalida.imprimir("Borrado completado");
		}else{
			entradaSalida.clienteNoEsta(dni);
		}
	}
	private static void ejecutarAñadirOfertaTarifa(CompanyiaTelefonica companyia){
		String dni=entradaSalida.pedirDni();
		entradaSalida.mostrarMenu(TipoOferta.getMenu());
		int opcion=entradaSalida.pedirOpcion();
		while(opcion<0||opcion>TipoOferta.getNumeroOpciones()){
			entradaSalida.imprimir("La opción seleccionada no existe");
			opcion=entradaSalida.pedirOpcion();
		}
		if(companyia.contains(dni)){
			FabricaTarifasConcr fab=new FabricaTarifasConcr();
			companyia.getCliente(dni).setTarifa(fab.getTarifa(companyia.getCliente(dni),TipoOferta.getOpcion(opcion)));
			entradaSalida.imprimir("Se ha modificado la tarifa");
		}else{
			entradaSalida.clienteNoEsta(dni);;
		}
	}
	private static void ejecutarRecuperarDatosCliente(CompanyiaTelefonica companyia){
		String dni=entradaSalida.pedirDni();
		if(companyia.contains(dni))
			entradaSalida.imprimir(companyia.datosClienteString(dni));
		else
			entradaSalida.clienteNoEsta(dni);
	}
	private static void ejecutarRecuperarTodosClientes(CompanyiaTelefonica companyia){
		ArrayList<Cliente> lista=companyia.listaClientes();
		if(lista.isEmpty()){
			entradaSalida.imprimir("No hay clientes");;
		}else{
			for(Cliente actual:lista){
				entradaSalida.imprimir("---------------------------------------");
				entradaSalida.imprimir(actual.toString());
				entradaSalida.imprimir("---------------------------------------");
			}
			entradaSalida.imprimir("Total clientes registrados: "+lista.size());
		}
	}
	private static void ejecutarClientesEntreFechas(CompanyiaTelefonica companyia){
		boolean salir=false;
		while(!salir){
			try{
				entradaSalida.imprimir("Introduce la fecha de inicio");
				LocalDate fechaIni=entradaSalida.pedirFecha();
				entradaSalida.imprimir("Introduce la fecha final");
				LocalDate fechaFin=entradaSalida.pedirFecha();
				Periodo periodo=new Periodo(fechaIni,fechaFin);
				ArrayList<Cliente> listaClientes=companyia.entreFechas(companyia.listaClientes(),periodo);
				if(listaClientes.isEmpty()){
					entradaSalida.imprimir("No existen clientes dados de alta entre "+fechaIni.toString()+" y "+fechaFin);
				}else{
					for(Cliente actual:listaClientes)
						entradaSalida.imprimir(actual.toString());
				}
				salir=true;
			}catch(PeriodoNoValidoExeption e){
				entradaSalida.imprimir(e.getMessage());
			}
		}
	}
	private static void ejecutarDarAltaLlamada(CompanyiaTelefonica companyia){
		String dni=entradaSalida.pedirDni();
		if(companyia.contains(dni)){
			Llamada llamadaNueva=entradaSalida.pedirLlamada();
			companyia.addLlamada(dni, llamadaNueva);
			entradaSalida.imprimir("La llamada ha sido añadida");
		}else{
			entradaSalida.clienteNoEsta(dni);
		}
	}
	private static void ejecutarMostrarLlamadasCliente(CompanyiaTelefonica companyia){
		String dni=entradaSalida.pedirDni();
		if(companyia.contains(dni)){
			entradaSalida.imprimir("Teléfono-año-día-mes-hora:minuto:segundo-duración");
			for(Llamada llamadaActual:companyia.listaLlamadasCliente(dni)){
			entradaSalida.imprimir(llamadaActual.toString());
			}
		}else{
			entradaSalida.clienteNoEsta(dni);
		}
	}
	private static void ejecutarMostrarLlamadasEntreFechas(CompanyiaTelefonica companyia){
		boolean salir=false;
		while(!salir){
			try{
				String dni=entradaSalida.pedirDni();
				if(companyia.contains(dni)){
					entradaSalida.imprimir("Introduce la fecha de inicio");
					LocalDate fechaIni=entradaSalida.pedirFecha();
					entradaSalida.imprimir("Introduce la fecha final");
					LocalDate fechaFin=entradaSalida.pedirFecha();
					Periodo periodo=new Periodo(fechaIni,fechaFin);
					ArrayList<Llamada> listaLlamadas=companyia.entreFechas(companyia.getCliente(dni).getListaLlamadas(),periodo);
					if(listaLlamadas.isEmpty()){
						entradaSalida.imprimir("No existen llamadas realizadas entre "+fechaIni.toString()+" y "+fechaFin);
					}else{
						for(Llamada actual:listaLlamadas)
							entradaSalida.imprimir(actual.toString());
					}
				}else{
					entradaSalida.clienteNoEsta(dni);
				}
				salir=true;
			}catch (PeriodoNoValidoExeption e){
				entradaSalida.imprimir(e.getMessage());
			}
		}
		
	}
	private static void ejecutarEmitirFactura(CompanyiaTelefonica companyia){
		boolean salir=false;
		while(!salir){
			try{
				String dni=entradaSalida.pedirDni();
				if(!companyia.contains(dni)){
					entradaSalida.clienteNoEsta(dni);
					break;
				}
				entradaSalida.imprimir("Fecha de facturación:");
				LocalDate fechaFactura=entradaSalida.pedirFecha();
				entradaSalida.imprimir("Fecha de inicio del periodo de facturación:");
				LocalDate fechaInicio=entradaSalida.pedirFecha();
				entradaSalida.imprimir("Fecha final del periodo de facturación:");
				LocalDate fechaFin=entradaSalida.pedirFecha();
				Factura fact=companyia.crearFactura(dni, fechaFactura, fechaInicio, fechaFin);
				companyia.addFactura(dni, fact);
				entradaSalida.imprimir("Se ha añadido al cliente "+dni+" la factura con ID: "+fact.getId());
				salir=true;
			}catch (PeriodoNoValidoExeption e) {
				entradaSalida.imprimir(e.getMessage());
			}
		}
	}
	private static void ejecutarRecuperarFactura(CompanyiaTelefonica companyia){
		int id=entradaSalida.pedirId();
		Factura fact=companyia.getFactura(id);
		if(fact==null)
			entradaSalida.imprimir("La factura con el id "+id+" no se ha encontrado");
		else
			entradaSalida.imprimir(companyia.getFactura(id).toString());
	}
	private static void ejecutarRecuperarTodasFacturasCliente(CompanyiaTelefonica companyia){
		String dni=entradaSalida.pedirDni();
		if(companyia.contains(dni)){
			ArrayList<Factura> listaFacturas=companyia.getFacturasCliente(dni);
			if(listaFacturas.isEmpty())
				entradaSalida.imprimir("El cliente no tiene facturas emitidas");
			else
				for(Factura actual:listaFacturas)
					entradaSalida.imprimir(actual.toString());
		}else{
			entradaSalida.clienteNoEsta(dni);	
		}	
	}
	private static void ejecutarMostrarFacturasEntreFechas(CompanyiaTelefonica companyia) {
		boolean salir=false;
		while(!salir){
			try{
				String dni=entradaSalida.pedirDni();
				if(companyia.contains(dni)){
					entradaSalida.imprimir("Introduce la fecha de inicio");
					LocalDate fechaIni=entradaSalida.pedirFecha();
					entradaSalida.imprimir("Introduce la fecha final");
					LocalDate fechaFin=entradaSalida.pedirFecha();
					Periodo periodo=new Periodo(fechaIni, fechaFin);
					ArrayList<Factura> listaFacturas=companyia.entreFechas(companyia.getCliente(dni).getListaFacturas(),periodo);
					if(listaFacturas.isEmpty()){
						entradaSalida.imprimir("No existen facturas realizadas entre "+fechaIni.toString()+" y "+fechaFin);
					}else{
						for(Factura actual:listaFacturas)
							entradaSalida.imprimir(actual.toString());
					}
				}else{
					entradaSalida.clienteNoEsta(dni);
				}
				salir=true;
			}catch(PeriodoNoValidoExeption e){
				entradaSalida.imprimir(e.getMessage());
			}
		}
		
		
		
	}
	private static void ejecutarGuardarCompanyia(CompanyiaTelefonica companyia){
		try{
			Guardar_Cargar.guardarCompanyia(companyia);
			entradaSalida.imprimir("Guardado completado");
		}catch (IOException e) {
			entradaSalida.imprimir("No se ha podido guardar la compañía correctamente");
		}
	}
	
}
