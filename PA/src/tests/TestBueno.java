package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.junit.*;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;

import clases.Cliente;
import clases.CompanyiaTelefonica;
import clases.Direccion;
import clases.Factura;
import clases.Particular;
import clases.Periodo;
import es.uji.www.GeneradorDatosINE;
import excepciones.PeriodoNoValidoExeption;
import tarifas.ConOferta;
import clases.Llamada;

public class TestBueno  {

	final static int NUMERO_CLIENTES=50;
	static CompanyiaTelefonica companyia;
	static String name,surname,NIF,email;
	static Direccion direccion;
	static Cliente cliente;
	static LocalDate fechaAlta=LocalDate.of(2000,5,15);
	static ConOferta tarifa;
	static Llamada llamada;
	static HashMap<Integer,Factura> facturasClientes;
	static Factura factura;
	static LocalDate fechaInicio=LocalDate.of(1,1,1);
	
	
	@BeforeClass
	public static void init() throws PeriodoNoValidoExeption{
		GeneradorDatosINE generador=new GeneradorDatosINE();
		name=generador.getNombre();
		surname=generador.getApellido();
		NIF=generador.getNIF();
		email="emailPrueba@uji.es";
		String provincia=generador.getProvincia();
		direccion=new Direccion(46018,provincia,generador.getPoblacion(provincia));
		tarifa=new ConOferta(10);
		fechaAlta=LocalDate.of(2000,5,5);
		cliente = new Particular(name,NIF,email,fechaAlta,direccion,tarifa,surname);
		llamada=new Llamada("644442380",LocalDate.of(2001,1,1),LocalTime.of(0,1,1),10);
	}
	
	@AfterClass
	public static void finish(){
		cliente=null;
	}
	
	@Before
	public void antesDe(){
		companyia=new CompanyiaTelefonica();
		companyia.addCliente(cliente);
		
	}
	@Test
	public void testAddLlamada(){
		companyia.addLlamada(NIF,llamada);
		assertEquals(true,companyia.getCliente(NIF).getListaLlamadas().contains(llamada));
	}
	@Test
	public void testListaLlamadasCliente(){
		ArrayList<Llamada> lista=new ArrayList<Llamada>();
		lista.add(llamada);
		companyia.addLlamada(NIF, llamada);
		assertEquals(lista,companyia.listaLlamadasCliente(NIF));
		
		
	}
	@Test
	public void testGetFacturasCliente() throws PeriodoNoValidoExeption{
		factura=companyia.crearFactura(NIF, fechaAlta, fechaInicio, fechaAlta);
		ArrayList<Factura> lista=new ArrayList<Factura>();
		lista.add(factura);
		companyia.addFactura(NIF, factura);
		factura=new Factura(2);
		lista.add(factura);
		companyia.addFactura(NIF, factura);
		assertEquals(lista,companyia.getFacturasCliente(NIF));
	}
	@Test
	public void testAddFacturaYGetFactura() throws PeriodoNoValidoExeption{
		factura=companyia.crearFactura(NIF, fechaAlta, fechaInicio, fechaAlta);
		companyia.addFactura(NIF, factura);
		assertEquals(factura, companyia.getFactura(1));
		
	}
	@Test
	public void testGetNombre(){
		assertEquals(companyia.getCliente(NIF).getNombre(),name);
	}
	@Test
	public void testGetNif(){
		assertEquals(companyia.getCliente(NIF).getNif(),NIF);
	}
	@Test
	public void testExcepcion(){
		try{
			Periodo periodo=new Periodo(LocalDate.of(1900,6,20),LocalDate.of(1900,6,21));
			
		}catch(PeriodoNoValidoExeption e){
			fail();
		}
		try{
			Periodo periodo=new Periodo(LocalDate.of(1999,6,5),LocalDate.of(1999, 6, 4));
			fail();
		}catch(PeriodoNoValidoExeption e){
			
		}
		
	}
	@Test
	public void testGetDireccion(){
		assertEquals(companyia.getCliente(NIF).getDireccion(),direccion);
	}
	
	@Test
	public void testGetTarifa(){
		assertEquals(tarifa,companyia.getCliente(NIF).getTarifa());
	}
	
	@Test
	public void testGetEmail(){
		assertEquals(companyia.getCliente(NIF).getEmail(),email);
	}
	
	@Test
	public void getCliente(){
		assertEquals(companyia.getCliente(NIF),cliente);
	}
	@Test
	public void testGenerico() throws PeriodoNoValidoExeption{
		factura=companyia.crearFactura(NIF, fechaAlta, fechaInicio, fechaAlta);
		cliente.addFactura(factura);
		ArrayList<Factura> list=new ArrayList<Factura>();
		ArrayList<Factura> list2=companyia.entreFechas(cliente.getListaFacturas(),new Periodo(fechaInicio, fechaAlta));
		list.add(factura);
		assertEquals(list,list2);
	}
	@Test
	public void testSetEmail(){
		String nuevoEmail="nuevo@uji.es";
		companyia.getCliente(NIF).setEmail(nuevoEmail);
		assertEquals(nuevoEmail,companyia.getCliente(NIF).getEmail());
	}
	@Test
	public void testBorrarClienteyContains(){
		assertEquals(true,companyia.contains(NIF));
		companyia.borrarCliente(NIF);
		assertEquals(null,companyia.getCliente(NIF));
		assertEquals(false,companyia.contains(NIF));
	}
	@Test
	public void testSetTarifa(){
		ConOferta nuevaTarifa=new ConOferta(20);
		companyia.getCliente(NIF).setTarifa(nuevaTarifa);
		assertEquals(companyia.getCliente(NIF).getTarifa(),nuevaTarifa);
		nuevaTarifa=new ConOferta(10);
		companyia.getCliente(NIF).setTarifa(tarifa);
		assertNotEquals(companyia.getCliente(NIF).getTarifa(),nuevaTarifa);
	}
	public void testBorrarLlamada(){
		companyia.borrarLlamada(NIF, factura.getId());
		assertTrue(companyia.listaLlamadasCliente(NIF).size()==1);
	}	
}
