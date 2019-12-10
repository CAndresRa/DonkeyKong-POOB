package aplicacion;

import java.awt.Color;
import java.io.Serializable;

public class GreenBarrel extends Barrel implements Serializable{
	private boolean destruido = false;
	private boolean dioVida = false;
	
	/**
	 * Constructor de clase barril verde
	 * @param posX
	 * @param posY
	 */
	public GreenBarrel(int posX, int posY) {
		super(posX, posY);
		setColor(Color.GREEN);
	}
	
	/**
	 * @deltaX indica pendiente de las plataformas 
	 */
	public void moveBarril(int deltaX) {
		super.moveBarril(deltaX);
	}
	
	/**
	 * Si el jugador es destructor y colisiona con barril gana una vida
	 * @param p jugador
	 */
	public boolean collisionPlayer(Player p) {
		Boolean seChocaron = false;
		if(p.getMyBounds().intersects(this.getBounds().getBounds2D())) {
			if(!dioVida && p.getDestructor()) {
				p.setInvertido(false);
				p.setLives(1);
				dioVida = true;
			}
			else if(!p.getDestructor()) {
				seChocaron = true;
			}
		}
		return seChocaron;	
	}
	public boolean getDestruido() {
		return destruido;
	}
}
