package clases;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import fabricas.FabricaClientes;
import fabricas.FabricaTarifasConcr;
import tarifas.Tarifa;
import tarifas.TarifaBasica;

public class Entrada_Salida {
	private Scanner sc;
	private PrintStream salida;
	
	
	public Entrada_Salida(){
		sc=new Scanner(System.in);
		salida=System.out;
	}
	public Entrada_Salida(InputStream entrada){
		this.sc=new Scanner(entrada);
		this.salida=System.out;
	}
	public Entrada_Salida(PrintStream salida){
		this.sc=new Scanner(System.in);
		this.salida=salida;
	}
	public Entrada_Salida(InputStream entrada,PrintStream salida){
		this.sc=new Scanner(entrada);
		this.salida=salida;
	}
	//Output
	public void mostrarMenu(String menu){
		salida.println(menu);
		salida.print("Elige una opción: ");
	}
	public void clienteNoEsta(String dni){
		salida.println("El cliente con NIF: "+dni+" no existe");
	}	
	public void imprimir(String cadena){
		salida.println(cadena);
	}
	//input
	public String pedirDni(){
		this.imprimir("Introduce el DNI del cliente");
		String dni=sc.nextLine();
		return dni;
	}	
	public Direccion pedirDireccion(){
		boolean correcto=false;
		int codigoPostal = 0;
		String provincia = null,poblacion = null;
		while(!correcto){
			try{
				this.imprimir("Introduce código postal");
				String aux=sc.nextLine();
				codigoPostal=Integer.parseInt(aux);
				this.imprimir("Introduce provincia");
				provincia=sc.nextLine();
				this.imprimir("Introduce población");
				poblacion=sc.nextLine();
				correcto=true;
			}catch(InputMismatchException e){
				imprimir("Los datos introducidos no son los que se esperaban");
			}catch(NumberFormatException e){
				imprimir("Tiene que ser un número entero");	
			}
		}
		return new Direccion(codigoPostal, provincia, poblacion);
	}	
	public LocalDate pedirFecha(){
		boolean correcto=false;
		LocalDate retFecha = null;
		while(!correcto){
			try{
			this.imprimir("Introduce fecha dd/mm/aaaa");
			String[] fecha=sc.nextLine().split("/");
			retFecha=LocalDate.of(Integer.parseInt(fecha[2]),Integer.parseInt(fecha[1]),Integer.parseInt(fecha[0]));
			correcto=true;
			}catch(PatternSyntaxException e){
				imprimir("El formato no es el correcto");
			}catch(DateTimeException e){
				imprimir(e.getMessage());
			}catch(ArrayIndexOutOfBoundsException e){
				imprimir("El formato no es el correcto");
			}catch(NumberFormatException e){
				imprimir("Tienen que ser números enteros");
			}
		}
		return retFecha;
	}
	public String pedirNif(){
		String nif;
		this.imprimir("Introduzca el nif");
		nif=sc.nextLine();
		return nif;
	}	
	public String pedirTelefono(){
		String tel;
		this.imprimir("Introduzca el teléfono");
		tel=sc.nextLine();
		return tel;
	}
	public int pedirDuracion(){
		boolean correcto=false;
		int dur = 0;
		while(!correcto){
			try{
				this.imprimir("Introduce la duración(segundos)");
				String aux=sc.nextLine();
				dur=Integer.parseInt(aux);
				correcto=true;
			}catch(NumberFormatException e){
				imprimir("Los datos introducidos no son los que se esperaban");
			}
		}
		return dur;
	}
	public Llamada pedirLlamada(){
		String telefonoDestino=pedirTelefono();
		LocalDate fecha=pedirFecha();
		LocalTime momento=pedirHora();
		int duracion=pedirDuracion();
		return new Llamada(telefonoDestino, fecha, momento, duracion);
		
	}
	public LocalTime pedirHora() {
		boolean correcto=false;
		LocalTime retHora=null;
		while(!correcto){
			try{
				imprimir("Formato de hora hh/mm/ss ");
				String[] hora=sc.nextLine().split("/");
				retHora=LocalTime.of(Integer.parseInt(hora[0]),Integer.parseInt(hora[1]),Integer.parseInt(hora[2]));
				correcto=true;
			}catch(InputMismatchException e){
				imprimir("Los datos introducidos no son los que se esperaban");
			}catch(NumberFormatException e){
				imprimir("Tienen que ser números enteros");
			}catch(ArrayIndexOutOfBoundsException e){
				imprimir("Los datos introducidos no son los que se esperaban");
			}catch(DateTimeException e){
				imprimir(e.getMessage());
			}
		}
		return retHora;
	}

	public  Cliente pedirCliente(){
		String name,nif,eMail,particular,apellidos = null;
		LocalDate fechaAlta;
		Direccion direccionCliente;
		Tarifa tarifaCl;
		imprimir("¿Nombre del cliente?");
		name=sc.nextLine();
		imprimir("¿Es particular?(y/n)");
		particular=sc.nextLine();
		if(particular.equals("y")){
			imprimir("Introduce los apellidos del cliente");
			apellidos=sc.nextLine();
		}
		imprimir("¿NIF del cliente?");
		nif=sc.nextLine();
		imprimir("¿eMail del cliente?");
		eMail=sc.nextLine();
		imprimir("Introduce ahora la fecha de alta del cliente");
		fechaAlta=pedirFecha();
		imprimir("Introduce ahora la dirección del cliente");
		direccionCliente=pedirDireccion();
		imprimir("La tarifa del cliente inicial será la básica");
		tarifaCl=new TarifaBasica();
		FabricaClientes fab=new FabricaClientes();
		if(apellidos==null)
			return fab.getEmpresa(name, nif, eMail, fechaAlta, direccionCliente, tarifaCl);
		else
			return fab.getParticular(name, nif, eMail, fechaAlta, direccionCliente, tarifaCl, apellidos);
		
	}
	public int pedirId(){
		boolean correcto=false;
		int id=0;
		imprimir("Introduce el código de la factura ");
		while(!correcto){
			try{
				String aux=sc.nextLine();
				id=Integer.parseInt(aux);
				correcto=true;
			}catch(NumberFormatException e){
				imprimir("Tiene que ser un número entero");
			}
		}
		return id;
	}
	public int pedirOpcion(){
		boolean correcto=false;
		int opcion=0;
		while(!correcto){
			try{
				String aux=sc.nextLine();
				opcion=Integer.parseInt(aux);
				correcto=true;
			}catch(NumberFormatException e){
				imprimir("Tiene que ser un número entero");
			}
		}
		return opcion;
	}
	public void cerrarEntrada(){
		sc.close();
	}
}