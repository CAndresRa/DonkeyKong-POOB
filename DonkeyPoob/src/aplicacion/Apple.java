package aplicacion;


import java.io.Serializable;

import javax.swing.ImageIcon;


public class Apple extends Surprise implements Serializable{
	/**
	 * Clase que ejecuta la sorpresa de la manzada del juego DonkeyPoob.
	 * @author: Andres Ramirez
	 * @version: 27/11/2019
	*/
	//"resources/apple.png";
	/**
     * Constructor para la clase Apple
	 * @param x La coordenada horizontal de la sorpresa.
	 * @param y La coordenada vertical de la sorpresa.
    */
	public Apple(int x , int y) {
		super(x,y);
		imagen = new ImageIcon("resourses/apple.png");
	}
	
	/**
	 * @param ruta de la imagen
	 */
	public void setImage(String ruta) {
		imagen = new ImageIcon(ruta);
	}
	
	/**
	 * otorga 5 puntos al jugador que tome la sorpresa
	 * @param player el jugador que tomo la sorpresa
	 */
	public void reaccione(Player player){
		player.setInvertido(false);
		player.setDestructor(false);
		player.setPoints(5);
	}
}
