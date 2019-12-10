package aplicacion;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Hammer extends Surprise implements Serializable{
	
	/**
	 * Constructor clase hammer
	 * @param posX
	 * @param posY
	 */
	public Hammer( int posX, int posY){
		super(posX,posY);
		imagen = new ImageIcon("resourses/hammer.png");
	}
	
	/**
	 * @param ruta imagen de un martillo
	 */
	public void setImage(String ruta) {
		imagen = new ImageIcon(ruta);
	}
	
	/**
	 * El jugador puede destruir algunos barriles 
	 */
	public void reaccione(Player player){
		player.setInvertido(false);
		player.setDestructor(true);
	}
}
