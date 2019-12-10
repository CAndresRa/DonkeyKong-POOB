package aplicacion;
import java.awt.geom.Line2D;
import java.io.Serializable;
public class Floor implements Serializable{
	private int posXi;
	private int posXf;
	private int posYi;
	private int posYf;
	private int pendiente;
    private Line2D.Double floor;
    
    /**
     * Constructor clase Floor
     * @param posXi
     * @param posYi
     * @param posXf
     * @param posYf
     */
	public Floor(int posXi, int posYi, int posXf, int posYf) {
		this.posXi = posXi;
		this.posYi = posYi;
		this.posXf = posXf;
		this.posYf = posYf;
		floor = new Line2D.Double (posXi, posYi, posXf, posYf);
		this.pendiente = calcularPendiente();
	}
	
	/**
	 * @return calcula la pendiente 
	 */
	private int calcularPendiente() {
		pendiente = 0;
		if(posYi > posYf) {pendiente = 1;}
		else if(posYi < posYf) {pendiente = -1;}
		return pendiente;
	}
	
	public int getPendiente() {
		return pendiente;
	}	
	
	public Line2D getFloor() {
		return floor;
	}
	
	public Line2D.Double getBounds() {
		return floor;
	}
	
	public int getYi() {
		return posYi;
	}
	
	public int getYf() {
		return posYf;
	}
	
}
