package tests;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Test;

import clases.Llamada;
import tarifas.*;



public class TestOfertas {


	Llamada llamada;
	//domingo de madrufada seran las 2 oferas que hay
	//coincide dia y franja (00)
	@Test
	public void testDiayFranja(){
		Tarifa tarifaCliente=new TarifaBasica();
		llamada = new Llamada("966850001", LocalDate.of(2017, 1, 1) , LocalTime.of(0, 0) ,10);
		Tarifa tarifaClienteDia = new OfertaDia(tarifaCliente,0,DayOfWeek.SUNDAY);
		Tarifa tarifaClienteHoras = new OfertaHoras(tarifaClienteDia, 0.15f, 0);
		
		assertEquals(0, tarifaClienteHoras.calcularCoste(llamada),0);
	}
		//assert that vale 0.0f
	
	//coincide dia, pero no feranja (01)
	@Test
	public void testDiayNoFranja(){
		Tarifa tarifaCliente=new TarifaBasica();
		llamada = new Llamada("966850001", LocalDate.of(2017, 1, 1) , LocalTime.of(15, 0) ,10);
		Tarifa tarifaClienteDia = new OfertaDia(tarifaCliente,0,DayOfWeek.SUNDAY);
		Tarifa tarifaClienteHoras = new OfertaHoras(tarifaClienteDia, 0.05f, 0);
		
		assertEquals(0, tarifaClienteHoras.calcularCoste(llamada),0);
	}
		//assert that vale 0.0f
	
	//coincide franja , pero no dia (10)
	@Test
	public void testNoDiayFranja(){
		llamada = new Llamada("966850001", LocalDate.of(2017, 1, 2) , LocalTime.of(0, 0) ,10);
		Tarifa tarifaCliente=new TarifaBasica();
		Tarifa tarifaClienteDia = new OfertaDia(tarifaCliente,0,DayOfWeek.SUNDAY);
		assertEquals(10*0.15f, tarifaClienteDia.calcularCoste(llamada),0);//no tiene q ser 0
		Tarifa tarifaClienteHoras = new OfertaHoras(tarifaClienteDia, 0.05f, 0);
		assertEquals(0.5f, tarifaClienteHoras.calcularCoste(llamada),0);
	}
		//assert that vale 0.5f
	
	//No se aplica ni dia ni franja (11)
	@Test
	public void testNoDiayNoFranja(){
		llamada = new Llamada("966850001", LocalDate.of(2017, 1, 2) , LocalTime.of(20, 0) ,10);
		Tarifa tarifaCliente=new TarifaBasica();
		Tarifa tarifaClienteDia = new OfertaDia(tarifaCliente,0,DayOfWeek.SUNDAY);
		Tarifa tarifaClienteHoras = new OfertaHoras(tarifaClienteDia, 0.05f, 0);
		assertEquals(llamada.getDuracion() * 0.15f, tarifaClienteHoras.calcularCoste(llamada),0);
	}
			//assert that vale 0.15f

}
