package enumeraciones;

public enum OpcionesCliente {
	ALTA_CLIENTE("Dar de alta un cliente(si está, se sobreescribirá)"),
	BORRAR("Borrar cliente"),
	CAMBIAR_TARIFA("Cambiar tarifa"),
	RECUPERAR_DATOS_CLIENTE("Recuperar datos cliente"),
	RECUPERAR_TODOS_CLIENTES("Recuperar datos de todos los clientes"),
	CLIENTES_ENTRE_FECHAS("Mostrar un listado de clientes que fueron dados de alta entre dos fechas"),
	SALIR("Atrás");
	
	private String descripcion;
	private OpcionesCliente(String descripcion){
		this.descripcion = descripcion;
	}
	public static String getMenu(){
		StringBuilder sb= new StringBuilder();
		for(OpcionesCliente opcion:OpcionesCliente.values()){
			sb.append(opcion.ordinal()+1);
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
	public static OpcionesCliente getOpcion(int posicion){
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
