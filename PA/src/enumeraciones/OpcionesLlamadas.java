package enumeraciones;

public enum OpcionesLlamadas {
	ALTA_LLAMADA("Dar de alta una llamada"),
	LLAMADAS_CLIENTE("Listar llamadas de un cliente"),
	LLAMADAS_ENTRE_FECHAS("Mostrar un listado de llamadas de un cliente que fueron realizadas entre dos fechas"),
	SALIR("Atrás");
	
	private String descripcion;
	private OpcionesLlamadas(String descripcion){
		this.descripcion = descripcion;
	}
	public static String getMenu(){
		StringBuilder sb= new StringBuilder();
		for(OpcionesLlamadas opcion:OpcionesLlamadas.values()){
			sb.append(opcion.ordinal()+1);
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
	public static OpcionesLlamadas getOpcion(int posicion){
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
