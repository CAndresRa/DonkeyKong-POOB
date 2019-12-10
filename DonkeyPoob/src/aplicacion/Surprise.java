package aplicacion;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.ImageIcon;

public abstract class Surprise implements Serializable{
	private int posX;
	private int posY;
	private Image temporal;
	private Rectangle2D figura;
	protected ImageIcon imagen;
	public Surprise(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		figura = new Rectangle2D.Double(posX,posY,15,15);
	}
	
	/**
     * Reaccion de la sorpesa al ser tomada.
	 * @param sorpresa La sorpresa del juego DonkeyPoob.
	 * @param jugador El jugador que tomo la sorpresa.
    */
	public abstract void reaccione(Player jugador) ;
	
	
	/**
     * Muestra la figura de la sorpresa.
	 * @return La figura de la sorpresa.
    */
	public Rectangle2D getFigura() {
		return figura;	
	}
	
	/**
     * Muestra la coordenada horizontal de la sorpresa.
	 * @return La coordenada horizontal de la sorpresa.
    */
	public int getX() {
		return (int) figura.getX();
	}
	
	/**
     * Muestra la coordenada vertical de la sorpresa.
	 * @return La coordenada vertical de la sorpresa.
    */
	public int getY() {
		return (int) figura.getY();
	}
	
	/**
     * Muestra la imagen de la sorpresa.
	 * @return La imagen de la sorpresa.
    */
	public Image getImage() {
		return imagen.getImage();
	}
}
