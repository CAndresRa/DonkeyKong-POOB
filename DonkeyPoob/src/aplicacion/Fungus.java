package aplicacion;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Fungus extends Surprise implements Serializable{
	/**
	 * Clase que ejecuta la sorpresa de la manzada del juego DonkeyPoob.
	 * @author: Andres Ramirez
	 * @version: 27/11/2019
	*/
	//
	/**
     * Constructor para la clase Fungus
	 * @param x La coordenada horizontal de la sorpresa.
	 * @param y La coordenada vertical de la sorpresa.
    */
	public Fungus(int x , int y) {
		super(x,y);
		imagen = new ImageIcon("resourses/hongo.png");
	}
	
	/**
	 * @param ruta de la imagen de un hongo 
	 */
	public void setImage(String ruta) {
		imagen = new ImageIcon(ruta);
	}
	
	
	/**
	 * Al jugador se le invierten los controles
	 * @param el jugador que toco la sorpresa
	 */
	public void reaccione(Player player){
		player.setDestructor(false);
		player.setInvertido(true);
	}
}
