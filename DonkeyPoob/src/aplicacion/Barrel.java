package aplicacion;
import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

public class Barrel  implements Serializable {
	private Ellipse2D.Double barril;
	private boolean sePuedeDestruir = true;
	private int posX = 200;
	protected int posY = 300;
	protected boolean touchStair, destruido;
	private Color color;
	private int puntos = 10;
	
	/**
	 * Contructor clase Barrel
	 * @param posX indica posicion x del barril
	 * @param posY indica posicion y del barril
	 */
	public Barrel(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		color = Color.yellow;
		destruido = false;
		barril = new Ellipse2D.Double(posX,posY,12,12);
	}
	
	/**
	 * Mantiene el barril bajando.
	 */
	public void gravedad() {
		posY = posY + 1;
		barril = new Ellipse2D.Double(posX,posY,12,12);
	}
	
	/**
	 * @return Rectangulo que encierra al barril 
	 */
	public Rectangle2D getBounds() {
		Rectangle2D barril = new Rectangle2D.Double(posX, posY, 12,17);
		return barril.getBounds();
	}
	
	/**
	 * Permite mover el barril 
	 * @param deltaX se le indica hacia que direccion mover el barril 
	 */
	public void moveBarril(int deltaX) {
		posX = posX - deltaX;
		posY = posY - 1; 
		barril = new Ellipse2D.Double(posX,posY,12,12);
	}  
	
	/**
	 * @param p Jugador 
	 * @return Detecta si el jugador choco contra con barril 
	 */
	public boolean collisionPlayer(Player p) {
		Boolean seChocaron = false;
		if(p.getMyBounds().intersects(this.getBounds().getBounds2D())) {
			p.setLives(-1);
			p.setInvertido(false);
			seChocaron = true;
					
		}
		return seChocaron;	
	}
	
	/**
	 * Permite conocer si el barril toca una escalera
	 * @param touch
	 */
	public void setTouchStair(boolean touch) {
		touchStair = touch;
	}
	
	/**
	 * Obtiene la figura elipse que es un barril
	 * @return retorna ellipse2d circulo del barril 
	 */
	public Ellipse2D.Double getBarrel(){
		return barril;
	}
	
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	
	public void setY(int cambioY) {
		posY = posY + cambioY;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean getSePuedeDestruir() {
		return sePuedeDestruir;
	}

	public boolean getDestruido() {
		return destruido;
	}
	
	public void setDestruido(boolean destroy) {
		this.destruido = destroy;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
}


