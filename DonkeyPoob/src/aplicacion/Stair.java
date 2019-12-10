package aplicacion;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
public class Stair implements Serializable{
	private Rectangle2D stair ;
	private int posX;
	private int posY;
	private int ancho;
	private int largo;
	private Color color;
	private boolean sePuedeEscalar;
	/**
	 * Contructor clase Stair
	 * @param posX indica posicion x de la escalera
	 * @param posY indica posicion y de la escalera
	 * @param ancho indica el ancho  de la escalera
	 * @param largo indica el largo de la escalera
	 */
	public Stair(int posX, int posY, int ancho,int largo) {
		stair = new Rectangle2D.Double(posX, posY, ancho,largo);
		sePuedeEscalar = true;
		color = Color.BLUE;
	}
	
	/**
	 * @return escalera
	 */
	public Rectangle2D getStair() {
		return stair;
	}
	
	public int  getXi() {
		return posX;
	}

	public int  getYi() {
		return posY;
	}
	
	public int  getAncho() {
		return ancho;
	}

	public int  getLargo() {
		return largo;
	}

	public void  setXi(int newX) {
		this.posX = newX;
	}

	public void  setYi(int newY) {
		this.posY = newY;
	}
	
	public void  setAncho(int newAncho) {
		this.ancho = newAncho;
	}

	public void  setLargo(int newLargo) {
		this.largo = newLargo;
	}
	
	public boolean getSePuedeEscalar() {
		return sePuedeEscalar;
	}
	
	public Color getColor() {
		return color;
	}
	
	
}
