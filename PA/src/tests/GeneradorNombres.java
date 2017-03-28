package tests;


import es.uji.www.GeneradorDatosINE;

public class GeneradorNombres {
	
	
	public static void main(String[] args) {
		GeneradorDatosINE generador = new GeneradorDatosINE();
	    System.out.println(imprime(generador));
	}
	
	private static String imprime(GeneradorDatosINE generador) {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre: " + generador.getNombre() + " " + generador.getApellido() + "\n");
		sb.append("NIF: " + generador.getNIF() + "\t" + "Edad: " + generador.getEdad() + "\n");
		String provincia = generador.getProvincia(); 
		sb.append("Domicilio: " + generador.getPoblacion(provincia) + " (" + provincia +")\n");
		
	    return sb.toString();	
	}

}
