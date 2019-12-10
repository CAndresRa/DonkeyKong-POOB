package aplicacion;
import java.awt.Color;
import java.io.Serializable;
public class RedBarrel extends Barrel implements Serializable{
	private boolean sePuedeDestruir = true;
	private int puntos = 30;
	public RedBarrel(int posX, int posY) {
		super(posX,posY);
		setColor(Color.RED);
	}
	
	/**
	 * @deltaX indica pendiente de la plataforma
	 */
	public void moveBarril(int deltaX) {
		if(touchStair) {
				setY(2);
		}
		super.moveBarril(deltaX);
	} 
	
	public boolean getSePuedeDestruir() {
		return sePuedeDestruir;
	}
	
	public int getPuntos() {
		return puntos;
	}
}
