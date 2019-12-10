package aplicacion;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Cherry extends Surprise implements Serializable{
	/**
	 * Clase que ejecuta la sorpresa de la manzada del juego DonkeyPoob.
	 * @author: Andres Ramirez
	 * @version: 27/11/2019
	*/
	//"resources/cherry.png";
	/**
     * Constructor para la clase Apple
	 * @param x La coordenada horizontal de la sorpresa.
	 * @param y La coordenada vertical de la sorpresa.
    */
	public Cherry(int x , int y) {
		super(x,y);
		imagen = new ImageIcon("resourses/cherry.png");
	}
	
	/**
	 * @param ruta de la imagen de una cereza
	 */
	public void setImage(String ruta) {
		imagen = new ImageIcon(ruta);
	}
	
	/**
	 * el jugador que toca la sorpresa se le incrementan
	 * 10 puntos
	 */
	public void reaccione(Player player){
		player.setInvertido(false);
		player.setDestructor(false);
		player.setPoints(10);
	}
}
