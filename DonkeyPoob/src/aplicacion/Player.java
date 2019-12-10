package aplicacion;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Player implements Serializable{
	private int posX = 100;
	private int posY = 685;
	private int points;
	private int lives;
	private Rectangle2D myBounds;
	private String name;
	private boolean jumping = false;
	private ImageIcon imagen;
	private Image temporal;
	private boolean invertido = false;
	private boolean destructor;
	private Floor plataformaActual;
	private boolean found;
	
	/**
	 * Constructor clase player
	 */
	public Player() {
		points = 0;
		lives = 3;
		destructor = false;
		myBounds = obtainMyBound();
	
	}
	
	/**
	 * @param name ruta de la imagen del jugador
	 */
	public void setName(String name) {
		this.name = name;
		imagen = new ImageIcon(name);
	}
	
	/**
	 * @return imagen que se puede pintar
	 */
	public Image getImage() {
		temporal = imagen.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon fin = new ImageIcon(temporal);
		return fin.getImage();
	}
	
	/**
	 * @return Rectangulo que permite utilizar intersects de la imagen 
	 */
	public Rectangle2D obtainMyBound() {
		Rectangle2D rectanguloImg = new Rectangle2D.Double(posX, posY, 30,35);
		return rectanguloImg;
	}
	
	/**
	 * @return rectangulo como tal 
	 */
	public Rectangle2D getMyBounds() {
		return obtainMyBound();
	}
	
	/**
	 * @param nextPosition Indica la pendiente de la plataforma donde esta 
	 */
	public void movePlayersRight(int nextPosition) {
		if(posX + 2 < 890){
			if(nextPosition == 0) {posX = posX + 1;}
			if(nextPosition == 1) {
				posX = posX + 2;
				posY = posY - 1;
			}
			if(nextPosition == -1) {
				posX = posX + 2;
				posY = posY - 1;
			}
			myBounds = new Rectangle2D.Double(posX, posY, 30,30);
		}
	}
	
	/**
	 * @param nextPosition Indica pendiente de la plataforma que toca
	 */
	public void movePlayersLeft(int nextPosition) {
		if(posX - 2 > 100) {
			if(nextPosition == 0) {posX = posX - 1;}
			if(nextPosition == -1) {
				posX = posX - 2;                                                                 
				posY = posY - 1;
			}
			if(nextPosition == 1) {
				posX = posX - 2;
				posY = posY - 1;
			}
			myBounds = new Rectangle2D.Double(posX, posY, 30,30);
		}
	}
	
	/**
	 * Si el jugador esta saltando y acciona derecha,
	 * este saltara a la derecha
	 */
	public void jumpRight() {
		for(int x = 0; x < 15; x++) {
			posX = posX + 2;
			posX = posX - 1;
		}
		posY = posY + 5;
	}
	
	/**
	 * Si el jugador esta saltando y acciona izquierda
	 * este saltara a la izquierda
	 */
	public void jumpLeft() {
		for(int x = 0; x < 15; x++) {
			posX = posX - 2;
			posX = posX + 1;
		}
		posY = posY + 5;
		
	}
	
	/**
	 * Si el jugador esta sobre una escalera, la puee escalar 
	 */
	public void goUp() {
		posY = posY - 2;
		myBounds = new Rectangle2D.Double(posX, posY, 30,30);
	}
	
	/**
	 * Si el jugador esta sobre una escalera, la puede bajar 
	 */
	public void goDown() {
		posY = posY + 1;
		myBounds = new Rectangle2D.Double(posX, posY, 30,30);	
	}

	
	/**
	 * Permite que los jugadores se mantengan en el suelo
	 */
	public void gravedad() {
		posY = posY + 1;
		myBounds = new Rectangle2D.Double(posX, posY, 30,30);
	}
	
	public void movePlayersUp() {

		posY = posY -45;
		myBounds = new Rectangle2D.Double(posX, posY, 30,30);		
	}
	
	public int getX() {
		return posX;
	}
	
	public void setX(int newX) {
		this.posX = newX ;
	}
	
	public int getY() {
		return posY;
	}
	
	public void setY(int newY) {
		this.posY = newY;
	}
	
	public boolean getJumping() {
		return jumping;
	} 
	public void setJumping(boolean jump) {
		this.jumping = jump;
	}
	
	public void setLives(int newLive) { 
		lives += newLive;
	}
	
	public void setPoints(int nuevoPuntaje) {
		points += nuevoPuntaje;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setInvertido(boolean isInvertido) {
		invertido = isInvertido;
	}
	
	public boolean getInvertido() {
		return invertido;
	}
	
	public Floor getPlataformaActual() {
		return plataformaActual;
	}
	public void setPlataformaActual(Floor f) {
		plataformaActual = f;
	}
	
	public void setDestructor(boolean destroyer) {
		destructor = destroyer;
	}
	
	public boolean getDestructor() {
		return destructor;
	}
	
	public String toString() {
		return name;
	}
	
	public void setFound(boolean found) {
		this.found = found; 
	}
	
	public boolean getFound() {
		return found;
	}
}
