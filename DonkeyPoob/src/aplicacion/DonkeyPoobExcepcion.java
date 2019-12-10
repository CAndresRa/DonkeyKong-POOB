package aplicacion;

import java.io.Serializable;

public class DonkeyPoobExcepcion extends Exception implements Serializable{
	
	public static final String SinBarriles = "No se encontraron barriles en la lista";
	public static final String COLOR_ERROR = "Hubo un error en color de un bloque";
	public static final String TYPE_DAT_ERROR = "La extension del archivo debe ser .dat";
	public static final String TYPE_TXT_ERROR = "La extension del archivo debe ser .txt";
	public static final String FILE_NOT_FOUND_ERROR = "El arhivo no pudo ser encontrado";
	public static final String NO_TEXT_FOUND = "El arhivo esta vacio";
	public static final String SIZE_ERROR = "El tamaño no corresponde al elemento";

		
	public DonkeyPoobExcepcion(String message){
		super(message);
	}
}

