package aplicacion;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.Serializable;
import java.security.DigestException;
import java.util.ArrayList;
import java.util.Arrays;

import persistencia.DonkeyPoobPersitencia;

public class DonkeyPoob implements Serializable{
	private ArrayList<Barrel> barrels;
	private ArrayList<Barrel> barrilesTemporales;
	private ArrayList<Stair> stairs;
	private ArrayList<Floor> floors;
	private ArrayList<Player> players;
	private ArrayList<Surprise> surprises;
	private ArrayList<Surprise> surprisesTemporales;
	private Donkey donkey;
	private Princesa princesa;
	private int contadorBarril = 1;
	private boolean reinicio = false;
	private ArrayList<String> barrilesSelect, sorpresasSelect;
	
	/**
	 * Constructor de la clase DonkeyPoob
	 */
	public DonkeyPoob(ArrayList<String> barrilesSelect, ArrayList<String> sorpresasSelect) {
		this.barrilesSelect = barrilesSelect;
		this.sorpresasSelect = sorpresasSelect;
		players = new ArrayList<>();
		floors = new ArrayList<>();
		barrels = new ArrayList<>();
		barrilesTemporales = new ArrayList<>();
		stairs = new ArrayList<>();
		surprises = new ArrayList<>();
	}
	
	/**
	 * Agrega un nuevo jugador
	 */
	public void addJugador() {
		Player newPlayer = new Player();
		players.add(newPlayer);
	}
	
	/**
	 * Agrega nuevo piso
	 * @param posXi
	 * @param posYi
	 * @param posXf
	 * @param posYf
	 */
	public void addFloor(int posXi, int posYi, int posXf, int posYf) {
		Floor newFloor = new Floor(posXi, posYi, posXf, posYf);
		floors.add(newFloor);
	}
	
	/**
	 * Agrega un nuevo barril
	 * @param barrel el nuevo barril 
	 */
	public void addBarrel (Barrel barrel) {
		barrels.add(barrel);
	}
	
	/**
	 * Agregar nuevas escaleras
	 * @param posX 
	 * @param posY
	 * @param ancho
	 * @param largo
	 */
	public void addStair(int posX, int posY, int ancho, int largo) {
		Stair newStair = new Stair( posX,posY,ancho,largo);
		stairs.add(newStair);
	}
	
	/**
	 * Agrega nueva ayuda
	 * @param help la nueva ayuda
	 */
	public void addSurprise(Surprise help) {
		surprises.add(help);
	}
	
	/**
	 * retorna jugador
	 * @return retorna jugador
	 */
	public Player getJugador() {
		return players.get(0);
	}
	
	public Player getJugador2() {
		return players.get(1);
	}
	
	/**
	 * se conecta con el timer 
	 * @throws DonkeyPoobExcepcion 
	 */
	public void juegue() throws DonkeyPoobExcepcion {
		gravedad();	
	}
	
	/**
	 * Mueve el jugador a la derecha
	 */
	public void movePlayersRight(Player p) {
		boolean enAire =  p.getJumping();
		int nextMove = isCollision(p);
		
		if( enAire) {
			p.jumpRight();
			p.setJumping(false);
		}
		else {p.movePlayersRight(nextMove);}
	}
	
	/**
	 * mueve el jugador a la izquierda
	 */
	public void movePlayersLeft(Player p) {
		boolean enAire =  p.getJumping();
		int nextMove = isCollision(p);
		choqueSorpresa();
		if(enAire) {
			p.jumpLeft();
			p.setJumping(false);}
		else {p.movePlayersLeft(nextMove);}	
	}
	
	/**
	 * Permite saltar o subir escaleras a los jugadores
	 */
	public void movePlayersUp(Player p) {
		isCollision(p);
		if(isCollisionStair(p)) {
			p.goUp();
		}
		else {
			if(p.getFound()) {
				p.setJumping(true);
				p.movePlayersUp();	
			}
		}
	}
	
	/**
	 * Indica al jugador que se mueva hacia abajo 
	 */
	public void movePlayersDown(Player p) {
		isCollision(p);
		if(isCollisionStair(p)) {
			p.goDown();
		}
	}
	
	/**
	 * Se ejecuta todo el tiempo, permite que los barriles,
	 * y jugadores caigan siempre.
	 * @throws DonkeyPoobExcepcion 
	 */
	public void gravedad() throws DonkeyPoobExcepcion {
		for(Player p : players) {
			isCollision(p);
			if(!p.getFound()) {
				p.gravedad();
			}
		}
		moveBarrel();
		touchStair();
		choqueSorpresa();
		isCollisionPlayer();
	}
	
	/*
	 * Permite idenficar hacia donde se debe mover el jugador dependiendo de la pendiente
	 * del piso.
	 */
	public int isCollision(Player p) {
		int collision = 0;
		for(Floor f: floors) {
			if(f.getBounds().intersects(p.getMyBounds().getBounds2D())) {
				collision = f.getPendiente();
				p.setJumping(false);
				p.setPlataformaActual(f);
				p.setFound(true);
				break;
			}
			else {
				p.setFound(false);
			}
		}
		return collision;
	}
	
	/**
	 * Se encarga de generar nuevos barriles y decidir hacia donde se mueven 
	 * @throws
	 */
	public void moveBarrel() throws DonkeyPoobExcepcion{
		try {
			if(barrilesTemporales.get(barrilesTemporales.size() - 1).getY() > 130) {
				barrilesTemporales.add(barrels.get(contadorBarril));
				contadorBarril += 1;
			}
			
			if(barrilesTemporales.get(0).getY() > 800){
				barrilesTemporales.remove(0);
				barrels.remove(0);
			}
			
			for(Barrel b: barrilesTemporales) {
				int deltaX = isCollisionBarrel(b);
				b.gravedad();
			
				if(deltaX != 0) {
					b.moveBarril(deltaX);
				}
			}
		}
		catch (IndexOutOfBoundsException e) {
			generarBarriles();
		}
    }
	
	/**
	 * Verifica si un barril esta sobre un piso
	 * @param b barril 
	 * @return pendiente del piso por el que se desplaza el barril 
	 */
	public int isCollisionBarrel(Barrel b) {
		int collision = 0;
		for(Floor f: floors) {
			if(f.getBounds().intersects(b.getBounds().getBounds2D())) {
				collision = f.getPendiente();
				break;
			}			
		}
		return collision;
	}
	
	
	/**
	 * Verifica si un barril toco a una escalera 
	 */
	public void touchStair() {
		for(Barrel b: barrels) {
			for(Stair s: stairs) {
				if(b.getBounds().intersects(s.getStair())) {
					b.setTouchStair(true);
					break;
				}
				else {
					b.setTouchStair(false);
				}
			}
		}
	}
	
	/**
	 * Verifica si un jugador toco a un barril
	 */
	public void isCollisionPlayer() {
		for(Player p : players) {
			for(Barrel b: barrilesTemporales) {
				if(b.collisionPlayer(p) && !p.getDestructor() && !b.getDestruido()) {
					perdioVida(p);
				}
				else if(b.collisionPlayer(p) && p.getDestructor() && !b.getDestruido()){
					p.setPoints(b.getPuntos());
					b.setDestruido(true);
				}
			}
		}
	}
	
	/**
	 * Verifica si el juagdor esta tocando una escalera
	 * @return booleano que verifica si el jugador esta tocando una escalera
	 */
	public boolean isCollisionStair(Player p) {
		boolean isStair = false;
		for(Stair s : stairs) {
			if(s.getStair().intersects(p.getMyBounds().getBounds2D()) && s.getSePuedeEscalar()) {
				isStair = true;
				break;
			}
		}
		return isStair;
	}
	
	/**
     * Determina si una sorpresa fue tocada por un jugador.
	 * @param sorpresa La sorpresa a revisar.
    */
	private void choqueSorpresa() {
		for(Player p : players) {
			for(Surprise s : surprisesTemporales) {
				if(p.getMyBounds().intersects(s.getFigura().getBounds().getBounds2D())) {
					s.reaccione(p);
					surprisesTemporales.remove(0);
					surprises.remove(0);
					surprisesTemporales.add(surprises.get(0));
				}
			}
		}
	}
	
	/**
	 * devuelve al jugador a la posicion inicial
	 * @param p player
	 */
	public void perdioVida(Player p) {
		//p.setLives(-1);
		reinicio = true;
		p.setX(100);
		p.setY(685);

	}
	
	/**
	 * 
	 * @return el boleano reinicio
	 */
	public boolean getReinicio() {
		return reinicio;
	}
	
	
	/*
	 * Crea el nivel
	 */
	public void createLevel() {
		donkey = new Donkey();
		princesa = new Princesa();
		addFloor(300,720,900,690);
		addFloor(100,575,800,605);
		addFloor(200,490,900,460);
		addFloor(100,340,800,370);
		addFloor(200,260,900,230);
		addFloor(100,720,300,720);
		addFloor(100,120,200,120);
		addFloor(200,120,800,150);
		addFloor(400,60,500,60);
		generarBarriles();
		addStair(700,598,10,86);
		addStair(300,483,10,86);
		addStair(700,365,10,86);
		addStair(300,253,10,86);
		addStair(750,146,10,78);
		addStair(490,38,10,83);
		Stair newStair = new stairBroken(500,590,10,40);
		stairs.add(newStair);
		generarSurprises();
	}
	
	/**
	 * Genera de forma aleatoria los barriles del nivel.
	 */
	private void generarBarriles() {
		Barrel barrilTemporal = null;
		for(int x = 0; x < 1000; x++) {
			int valorBarril = (int) Math.floor(Math.random()*barrilesSelect.size());
			if(barrilesSelect.get(valorBarril).equals("amarillo")) {barrilTemporal =  new Barrel(201,100);}
			else if(barrilesSelect.get(valorBarril).equals("azul")) {barrilTemporal =  new BlueBarrel(201,100);}
			else if(barrilesSelect.get(valorBarril).equals("rojo")) {barrilTemporal =  new RedBarrel(201,100);}
			else if(barrilesSelect.get(valorBarril).equals("verde")) {barrilTemporal =  new GreenBarrel(201,100);}
			addBarrel(barrilTemporal);
		}
		barrilesTemporales = new ArrayList<>();
		barrilesTemporales.add(barrels.get(0));
	}
	
	/**
	 * Genera aleatoriamente las sorpresas del nivel.
	 */
	private void generarSurprises() {
		int[][] posSorpresas = {{400,300},{490,430},{200,540},{700,200}};  
		Surprise sorpresa = null;
		for(int x = 0; x < 1000; x++) {
			int valorPosition = (int) Math.floor(Math.random()*4);
			int valorDado = (int) Math.floor(Math.random()*sorpresasSelect.size()); 
			if(sorpresasSelect.get(valorDado).equals("manzana")) {sorpresa = new Apple(posSorpresas[valorPosition][0],posSorpresas[valorPosition][1]);}
			else if(sorpresasSelect.get(valorDado).equals("vida")) {sorpresa = new Life(posSorpresas[valorPosition][0],posSorpresas[valorPosition][1]);}
			else if(sorpresasSelect.get(valorDado).equals("cereza")) {sorpresa = new Cherry(posSorpresas[valorPosition][0],posSorpresas[valorPosition][1]);}
			else if(sorpresasSelect.get(valorDado).equals("hongo")) {sorpresa = new Fungus(posSorpresas[valorPosition][0],posSorpresas[valorPosition][1]);}
			else if(sorpresasSelect.get(valorDado).equals("hammer")) {sorpresa = new Hammer(posSorpresas[valorPosition][0],posSorpresas[valorPosition][1]);}
			addSurprise(sorpresa);
		}
		surprisesTemporales = new ArrayList<>();
		surprisesTemporales.add(surprises.get(0));
	}
	
	/**
     * Retorna una sorpresa del juego DonkeyPoob.
	 * @param index El indice de la sorpresa en la lista de sorpresas del juego DonkeyPoob.
     * @return La sorpresa del juego DonkeyPoob.
    */
	public Surprise getSurprise(int index){
		return surprisesTemporales.get(index);
	}
	
	/**
	 * @return Longitud Surprises
	 */
	public int getLenSurprises() {
		return surprisesTemporales.size();
	}
	
	/**
     * Retorna un barril del juego DonkeyPoob.
	 * @param index El indice en la lista de barrilesTemporales del juego DonkeyPoob.
     * @return El barril del juego DonkeyPoob.
    */
	public Barrel getBarrel(int index){
		return barrilesTemporales.get(index);
	}
	
	/**
	 * @return Longitud barrels
	 */
	public int getLenBarrels() {
		return barrilesTemporales.size();
	}
	
	/**
     * retorna una plataforma del juego DonkeyPoob.
	 * @param index El indice en la lista de floors del juego DonkeyPoob.
     * @return La plataforma del juego DonkeyPoob.
    */
	public Floor getFloor(int index){
		return floors.get(index);
	}
	
	/**
	 * @return Longitud Floors
	 */
	public int getLenFloors() {
		return floors.size();
	}
	
	/**
     * retorna una Escalera del juego DonkeyPoob.
	 * @param index El indice en la lista de Stairs del juego DonkeyPoob.
     * @return La Escalera del juego DonkeyPoob.
    */
	public Stair getStair(int index){
		return stairs.get(index);
	}
	
	/**
	 * @return Longitud stairs
	 */
	public int getLenStairs() {
		return stairs.size();
	}
	
	/**
	 * @return numero de vidas restantes.
	 */
	public int getLives() {
		return players.get(0).getLives();
	}
	
	/**
	 * @param b el barril a pintar
	 * @return color del barril b 
	 */
	public Color getColorBarril(Barrel b){
		return b.getColor();
	}
	
	/**
	 * ayuda  a conocer el estado del jugador si toma la sorpresa hongo
	 * @return invertido 
	 */
	public boolean getInvertido() {
		return players.get(0).getInvertido();
	}
	
	/**
	 * indica si gano el juego
	 * @return booleano
	 */
	public boolean ganoJuego() {
		return players.get(0).getPlataformaActual().getYf() == 60;
	}
	
	/**
	 * se obtiene a donkey
	 * @return donkey
	 */
	public Donkey getDonkey() {
		return donkey;
	}
	
	public Princesa getPrincesa() {
		return princesa;
	}
	
	/**
	 * @return los puntos del jugador
	 */
	public int getPoints() {
		return players.get(0).getPoints();
	}
	
	/**
	 * @return Si el jugador tomo la sorpresa martillo
	 */
	public boolean isPlayerDestroyer() {
		return players.get(0).getDestructor();
	}
	
	/**
	 * @return Si el jugador esta bajo el efecto de la sorpresa
	 * Hongo
	 */
	public boolean getInvertido2() {
		return players.get(0).getDestructor();
	}
	
	/**
     * Guarda el arkapoob desde un archivo con extension (.arka)
	 * @param file El archivo donde se va a guardar el arkapoob.
	 * @throws ArkanoidException - TYPE_ARKA_ERROR Si el archivo no tiene la extension .arka.
	 * @throws ArkanoidException - FILE_NOT_FOUND_ERROR Si no se encontro el archivo para guardar el arkapoob.
	 * @throws ArkanoidException Si ocurrio un error al serializar el archivo.
    */
	public void salvar(File file) throws DonkeyPoobExcepcion{
		DonkeyPoobPersitencia.save(this,file);
	}
}
