package fabricas;

import java.time.DayOfWeek;
import java.util.Scanner;

import clases.Cliente;
import enumeraciones.TipoOferta;
import interfaces.FabricaTarifas;
import tarifas.Tarifa;
import tarifas.TarifaBasica;
import tarifas.OfertaDia;
import tarifas.OfertaHoras;

public class FabricaTarifasConcr implements FabricaTarifas {

	@Override
	public Tarifa getTarifa(Cliente cliente, TipoOferta tipoOferta) 
	{
		Tarifa tarifa = cliente.getTarifa();
		switch(tipoOferta)
		{
		case TARIFA_BASICA:
			tarifa = new TarifaBasica();
			break;
			
		case TARIFA_HORAS:
			System.out.println("Escoja al franja horaria en la que quiera aplicar el descuento:");
			System.out.println("0: Des de las 00:00, hasta las 8:00");
			System.out.println("1: Des de las 8:00, hasta las 16:00");
			System.out.println("2: Des de las 16:00, hasta las 0:00");
			Scanner sc = new Scanner(System.in);
		    int franja = sc.nextInt();
		    sc.nextLine();
			tarifa = new OfertaHoras(tarifa, 0.05f, franja);
			break;
			
		case TARIFA_DIA:
			System.out.println("Escoja el dia de la semana que quiere oferta");
			System.out.println("Siendo lunes=1, martes=2 ...");
			Scanner sc2 = new Scanner(System.in);
		    int diaDeOferta = sc2.nextInt();
		    sc2.nextLine();
			tarifa = new OfertaDia(tarifa, 0,DayOfWeek.of(diaDeOferta));
			break;
		}
		return tarifa;
	}

}

//cambiar construcoteres de ConOferta para que se elija
//dentro del mismo que dia quieres oferta