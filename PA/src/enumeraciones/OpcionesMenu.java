package enumeraciones;

public enum OpcionesMenu{
	OPCIONES_CLIENTE("Mostrar las opciones de los clientes"),
	OPCIONES_LLAMADAS("Mostrar las opciones de las llamadas"),
	OPCIONES_FACTURAS("Mostrar las opciones de las facturas"),
	GUARDAR_COMPANYIA("Guarda los datos actuales que contiene la compañía"),
	SALIR("Salir");
	
	private String descripcion;
	private OpcionesMenu(String descripcion){
		this.descripcion = descripcion;
	}
	public static String getMenu(){
		StringBuilder sb= new StringBuilder();
		for(OpcionesMenu opcion:OpcionesMenu.values()){
			sb.append(opcion.ordinal()+1);
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
	public static OpcionesMenu getOpcion(int posicion){
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
