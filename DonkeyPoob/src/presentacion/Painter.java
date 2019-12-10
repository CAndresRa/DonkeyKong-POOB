package presentacion;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import aplicacion.*;
import java.io.*;
import java.util.ArrayList;

public class Painter extends JPanel{
	private DonkeyPoobGUI gameGUI;
	private DonkeyPoob game;
	private Graphics2D g2;
	private boolean right,left, up, down;
	private boolean right2,left2, up2, down2;
	private Timer timerPlayer, timerGame;
	private int modo;
	private ArrayList<String> barrilesSelect, sorpresasSelect;
	private String cpuSelect;
	
	public Painter(DonkeyPoobGUI gameGUI, int modo, ArrayList<String> barrilesSelect, ArrayList<String> sorpresasSelect, String cpuSelect) {
		this.gameGUI = gameGUI;
		this.modo = modo;
		this.barrilesSelect = barrilesSelect;
		this.sorpresasSelect = sorpresasSelect;
		right = false;
		left = false;
		up = false;
		down = false;
		right2 = false;
		left2 = false;
		up2 = false;
		down2 = false;
		setBackground(Color.black);
		reiniciarTimers();
		prepareAcciones();
		prepareJuego();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		prepareDibujos(g);

	}
	
	private void prepareDibujos(Graphics g) {
		game.getDonkey().setName("resourses/donkey.png");
		Image imagen1 = game.getDonkey().getImage();
		g.drawImage(imagen1, game.getDonkey().getX(), game.getDonkey().getY(), this);
		
		game.getPrincesa().setName("resourses/princesa.png");
		Image imagen2 = game.getPrincesa().getImage();
		g.drawImage(imagen2, game.getPrincesa().getX(), game.getDonkey().getY(), this);
		

		g2.setPaint(Color.red);
		int nFloors = game.getLenFloors(); 
		for(int x = 0; x < nFloors; x++) {
			g2.setStroke(new BasicStroke(10)); 
			g2.draw(game.getFloor(x).getFloor());
			g2.setPaint(Color.red);
		}
		
		g2.setPaint(Color.blue);
		int nStairs = game.getLenStairs(); 
		for(int x = 0; x < nStairs; x++) { 
			g2.draw(game.getStair(x).getStair());
			g2.setPaint(game.getStair(x).getColor());
		}

		
		g2.setPaint(Color.WHITE);
		int nSurprises = game.getLenSurprises(); 
		for(int x = 0; x < nSurprises; x++) {
			Image imagen = game.getSurprise(x).getImage();
			Image temporal = imagen.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
			ImageIcon fin = new ImageIcon(temporal);
			g.drawImage(fin.getImage(), game.getSurprise(x).getX(), game.getSurprise(x).getY(), this);

		}
		
		
		if(right) {
			game.getJugador().setName("resourses/mario.png");
			Image imagen = game.getJugador().getImage();
			g.drawImage(imagen, game.getJugador().getX(), game.getJugador().getY(), this);
		}
		else if(left) {
			game.getJugador().setName("resourses/marioLeft.png");
			Image imagen = game.getJugador().getImage();
			g.drawImage(imagen, game.getJugador().getX(), game.getJugador().getY(), this);
		}
		else if(up){
			game.getJugador().setName("resourses/mario.png");
			Image imagen = game.getJugador().getImage();
			g.drawImage(imagen, game.getJugador().getX(), game.getJugador().getY(), this);
		}
		else {
			game.getJugador().setName("resourses/mario.png");
			Image imagen = game.getJugador().getImage();
			g.drawImage(imagen, game.getJugador().getX(), game.getJugador().getY(), this);
			
		}
		
		if(modo == 2) {
			if(right2) {
				game.getJugador2().setName("resourses/manzana.png");
				Image imagen = game.getJugador2().getImage();
				g.drawImage(imagen, game.getJugador2().getX(), game.getJugador2().getY(), this);
			}
			else if(left2) {
				game.getJugador2().setName("resourses/manzana.png");
				Image imagen = game.getJugador2().getImage();
				g.drawImage(imagen, game.getJugador2().getX(), game.getJugador2().getY(), this);
			}
			else if(up2){
				game.getJugador2().setName("resourses/manzana.png");
				Image imagen = game.getJugador2().getImage();
				g.drawImage(imagen, game.getJugador2().getX(), game.getJugador2().getY(), this);
			}
			else {
				game.getJugador2().setName("resourses/manzana.png");
				Image imagen = game.getJugador2().getImage();
				g.drawImage(imagen, game.getJugador2().getX(), game.getJugador2().getY(), this);                        
			}
			
		}
		
		int nBarriles = game.getLenBarrels(); 
		for(int x = 0; x < nBarriles; x++) {
			if(!game.getBarrel(x).getDestruido()) {
				g2.setPaint(game.getBarrel(x).getColor());
				g2.draw(game.getBarrel(x).getBarrel());
				g2.fill(game.getBarrel(x).getBarrel());
			}
		}
	}
	
	public void accionJugar() throws DonkeyPoobExcepcion {
		activeTimers();
		int vidas1 = game.getLives();
		int puntajeP1 = game.getPoints(); 
		gameGUI.actualizaResultados(vidas1, puntajeP1);
		game.juegue();
		repaint();
		if(game.ganoJuego()) {
			int confirmado = JOptionPane.showConfirmDialog(null, "Ganaste!!! ¿Quieres jugar nuevamente?", "gano",JOptionPane.YES_NO_OPTION);
			if (confirmado == 0) {
				stopTimers();
				nuevoJuego();
			}
			else {
				System.exit(0);
			}
		}
		if(game.getLives() == 0) {
			int confirmado = JOptionPane.showConfirmDialog(null, "Perdio!!!  ¿Quieres jugar nuevamente?", "perdio",JOptionPane.YES_NO_OPTION);
			if (confirmado == 0) {
				stopTimers();
				nuevoJuego();
			}
			else {
				System.exit(0);
			}
		}
		
	}
	public void nuevoJuego() {
		prepareJuego();
	}
	
	private void prepareAcciones()  {
		addKeyListener(new KeyAdapter() {
			public void keyPressed( KeyEvent e ) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					right = true;
					accionMuevaPlayer();
				}
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					left = true;
					accionMuevaPlayer();
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					up = true;
					accionMuevaPlayer();
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					down = true;
					accionMuevaPlayer();
				}
				
				if(modo == 2) {
					if (e.getKeyCode() == KeyEvent.VK_D) {
						right2 = true;
						accionMuevaPlayer2();
					}
					else if (e.getKeyCode() == KeyEvent.VK_A) {
						left2 = true;
						accionMuevaPlayer2();
					}
					else if (e.getKeyCode() == KeyEvent.VK_W) {
						up2 = true;
						accionMuevaPlayer2();
					}
					else if (e.getKeyCode() == KeyEvent.VK_S) {
						down2 = true;
						accionMuevaPlayer2();
					}
				}
				

			}
			public void keyReleased( KeyEvent e ) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					right = false;

				}
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					left = false;

				}
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					up = false;
	
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					down = false;
				}
				
				
				if (e.getKeyCode() == KeyEvent.VK_D) {
					right2 = false;

				}
				else if (e.getKeyCode() == KeyEvent.VK_A) {
					left2 = false;

				}
				else if (e.getKeyCode() == KeyEvent.VK_W) {
					up2 = false;
	
				}
				else if (e.getKeyCode() == KeyEvent.VK_S) {
					down2 = false;
				}
			}
			
		});
	}
	
	private void accionMuevaPlayer() {
		boolean invertido = game.getInvertido();
		if(right && invertido == false){
			game.movePlayersRight(game.getJugador());
		}
		if(right && invertido == true) {
			game.movePlayersLeft(game.getJugador());
		}
		if(left && invertido == false) {
;			game.movePlayersLeft(game.getJugador());
		}
		if(left && invertido == true) {
			game.movePlayersRight(game.getJugador());
		}
		if(up) {
			game.movePlayersUp(game.getJugador());
		}
		if(down) {
			game.movePlayersDown(game.getJugador());
		}
	}
	
	
	private void accionMuevaPlayer2() {
		boolean invertido = game.getInvertido2();
		if(right2 && invertido == false){
			game.movePlayersRight(game.getJugador2());
		}
		if(right2 && invertido == true) {
			game.movePlayersLeft(game.getJugador2());
		}
		if(left2 && invertido == false) {
;			game.movePlayersLeft(game.getJugador2());
		}
		if(left2 && invertido == true) {
			game.movePlayersRight(game.getJugador2());
		}
		if(up2) {
			game.movePlayersUp(game.getJugador2());
		}
		if(down2) {
			game.movePlayersDown(game.getJugador2());
		}
	}


	private void prepareJuego(){
		game = new DonkeyPoob(barrilesSelect, sorpresasSelect);
		game.createLevel();
		prepareJugadores();
	}
	
	private void prepareJugadores() {
		if(modo == 1) {
			game.addJugador();
		}
		else if(modo == 2) {
			game.addJugador();
			game.addJugador();
		}
	}
	
	public void stopTimers() {
		timerGame.stop();
		timerPlayer.stop();
	}
	private void activeTimers() {
		timerGame.start();
		timerPlayer.start();
	}
	private void reiniciarTimers() {
		timerGame = new Timer(15, new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				try {
					accionJugar();
				} catch (DonkeyPoobExcepcion e1) {
					e1.printStackTrace();
				}
			} 
		});
		timerPlayer = new Timer(10, new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				setFocusable(true);
				requestFocusInWindow();
				accionMuevaPlayer();
			} 
		}); 
	}
	
	public void salvar(File file) throws DonkeyPoobExcepcion{
		game.salvar(file);
	}

}
