package enumeraciones;

public enum TipoOferta {
	TARIFA_BASICA("Precio por defecto, sin aplicar descuento(Se iniciará la tarifa básica sin nada más)"),
	TARIFA_HORAS("Escoger uno de los 3 rangos de 8 horas para pagar 0.05f por minuto(Se añade a la tarifa actual)"),
	TARIFA_DIA("Un dia de la semana completo gratis todas las llamadas(Se añade a la tarifa actual)");
	
	private String descripcion;
	private TipoOferta(String descipcion){
		this.descripcion=descipcion;
	}
	public String getDescripcion(){
		return descripcion;
	}
	public static String getMenu(){
		StringBuilder sb= new StringBuilder();
		for(TipoOferta opcion:TipoOferta.values()){
			sb.append(opcion.ordinal()+1);
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
	public static TipoOferta getOpcion(int posicion){
		return values()[posicion-1];
	}
	public static int getNumeroOpciones()
	{
		return values().length;
	}
}
