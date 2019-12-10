package aplicacion;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;



public class Donkey implements Serializable{
	private int posX, posY;
	private ImageIcon imagen;
	private Image temporal;
	private String name;
	public Donkey(){
		posX = 100;
		posY = 34;
	}
	
	public void setName(String name) {
		this.name = name;
		imagen = new ImageIcon(name);
	}
	
	public Image getImage() {
		temporal = imagen.getImage().getScaledInstance(96, 90, Image.SCALE_SMOOTH);
		ImageIcon fin = new ImageIcon(temporal);
		return fin.getImage();
	}
	
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
}
