package aplicacion;

import java.awt.Color;
import java.io.Serializable;


public class BlueBarrel extends Barrel implements Serializable{
	private boolean sePuedeDestruir = true;
	private int puntos = 20;
	public BlueBarrel(int posX , int posY) {
		super(posX,posY);
		setColor(Color.BLUE);
	}
	
	@Override
	public void moveBarril(int deltaX) {
		gravedad();
	} 
	
	public boolean getSePuedeDestruir() {
		return sePuedeDestruir;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
}
