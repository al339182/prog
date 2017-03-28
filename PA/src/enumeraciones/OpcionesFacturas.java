package enumeraciones;

public enum OpcionesFacturas {
	EMITIR_FACTURA("Emitir una factura para un cliente, calculando el importe de la misma en función de las llamadas"),
	RECUPERAR_FACTURA("Recuperar los datos de una factura a partir de su código"),
	RECUPERAR_TODAS_FACTURAS_CLIENTE("Recuperar todas las facturas de un cliente"),
	FACTURAS_ENTRE_FECHAS("Mostrar el listado de facturas de un cliente emitidas entre dos fechas"),
	ATRAS("Atrás");
	
	private String descripcion;
	private OpcionesFacturas(String descripcion){
		this.descripcion = descripcion;
	}
	public static String getMenu(){
		StringBuilder sb= new StringBuilder();
		for(OpcionesFacturas opcion:OpcionesFacturas.values()){
			sb.append(opcion.ordinal()+1);
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
	public static OpcionesFacturas getOpcion(int posicion){
		return values()[posicion-1];
	}
	public String getDescripcion(){
		return descripcion;
	}
	public static int getNumeroOpciones()
	{
		return values().length;
	}
}
