package interfaces;

import clases.Cliente;
import enumeraciones.TipoOferta;
import tarifas.Tarifa;

public interface FabricaTarifas {
	public Tarifa getTarifa(Cliente cliente, TipoOferta tipoOferta);
}
