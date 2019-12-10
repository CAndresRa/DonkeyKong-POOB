package aplicacion;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Life extends Surprise implements Serializable{
	/**
	 * Clase que ejecuta la sorpresa de la manzada del juego DonkeyPoob.
	 * @author: Andres Ramirez
	 * @version: 27/11/2019
	*/
	//"resources/heart.png";
	/**
     * Constructor para la clase Apple
	 * @param x La coordenada horizontal de la sorpresa.
	 * @param y La coordenada vertical de la sorpresa.
    */
	public Life(int x , int y) {
		super(x,y);
		imagen = new ImageIcon("resourses/heart.png");
	}
	
	/**
	 * @param ruta de imagen corazon
	 */
	public void setImage(String ruta) {
		imagen = new ImageIcon(ruta);
	}
	
	/**
	 * El jugador gana una vida
	 */
	public void reaccione(Player player){
		player.setDestructor(false);
		player.setInvertido(false);
		player.setLives(1);
	}
}
