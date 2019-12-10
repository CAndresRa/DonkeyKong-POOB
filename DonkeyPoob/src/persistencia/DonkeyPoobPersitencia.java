package persistencia;
import java.io.*;
import java.lang.reflect.*;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

import aplicacion.*;

public abstract class DonkeyPoobPersitencia {
	public static String nombre="DonkeyPoob";

	public static DonkeyPoob open(File file) throws DonkeyPoobExcepcion{
		DonkeyPoob game = null;
		if (!file.getName().endsWith(".dat")) throw new DonkeyPoobExcepcion(DonkeyPoobExcepcion.TYPE_DAT_ERROR);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			game = (DonkeyPoob) in.readObject();
			in.close();
		}catch(ClassNotFoundException e) {
			throw new DonkeyPoobExcepcion(DonkeyPoobExcepcion.FILE_NOT_FOUND_ERROR);
		}catch (IOException e) {
			throw new DonkeyPoobExcepcion("Ocurrio un error al abrir el archivo" + file.getName());
		}
		return game;
	}
	public static void save(DonkeyPoob game, File file) throws DonkeyPoobExcepcion{
		if (!file.getName().endsWith(".dat")) throw new DonkeyPoobExcepcion(DonkeyPoobExcepcion.TYPE_DAT_ERROR);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(game);
			out.close();
		}catch (IOException e) {
			throw new DonkeyPoobExcepcion("Ocurrio un error al salvar " + file.getName());
		}
	}
	
	/**
     * Escribe en el log de errores una excepcion.
	 * @param e La excepcion a escribir.
    */
    public static void registre(Exception e){
        try{
            Logger logger=Logger.getLogger(nombre);
            logger.setUseParentHandlers(false);
            FileHandler file=new FileHandler(nombre+".log",true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE,e.toString(),e);
            file.close();
        }catch (Exception oe){
            oe.printStackTrace();
            System.exit(0);
        }
    }
}
