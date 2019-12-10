package aplicacion;

import java.awt.Color;
import java.io.Serializable;

public class stairBroken extends Stair implements Serializable{
	private boolean sePuedeEscalar;
	private Color color;
	public stairBroken(int posX, int posY, int ancho,int largo) {
		super(posX,posY,ancho,largo);
		sePuedeEscalar = false;
		color = Color.MAGENTA;
	}
	
	public boolean getSePuedeEscalar() {
		return sePuedeEscalar;
	}
	public Color getColor() {
		return color;
	}
}
