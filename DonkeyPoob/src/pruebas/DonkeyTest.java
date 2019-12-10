package pruebas;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import aplicacion.*;

/**
* The test class DonkeyPoob.
*
* @author  (Andres Ramirez)
* @version (1)
*/

public class DonkeyTest {
	@Test
	public void elJugadorDeberiaMoverseHaciaLaDerecha() {
		Player a = new Player();
		int posInicial = a.getX();
		for(int x = 0; x < 100; x++) {
			a.movePlayersRight(0);
		}
		int posFinal = a.getX();
		assertTrue(posFinal == posInicial + 100);	
	}
	
	@Test
	public void elJugadorDeberiaMoversHaciaLaIzquierda() {
		Player a = new Player();
		a.setX(500);
		int posInicial = a.getX();
		for(int x = 0; x < 100; x++) {
			a.movePlayersLeft(0);
		}
		int posFinal = a.getX();
		assertTrue(posFinal == posInicial - 100);	
	}
	
	@Test
	public void elJugadorDeberiaSaltar() {
		Player a = new Player();
		int posInicial = a.getY();
		a.movePlayersUp();
		int posFinal = a.getY();
		assertTrue(posFinal == posInicial - 45);	
	}
	
	@Test
	public void elJugadorDeberiaSaltarHaciaLaDerecha() {
		Player a = new Player();
		int posInicial = a.getX();
		a.movePlayersUp();
		a.jumpRight();
		int posFinal = a.getX();
		assertTrue(posFinal == posInicial + 15);	
	}
	
	@Test
	public void elJugadorDeberiaSaltarHaciaLaIzquierda() {
		Player a = new Player();
		int posInicial = a.getX();
		a.movePlayersUp();
		a.jumpLeft();
		int posFinal = a.getX();
		assertTrue(posFinal == posInicial - 15);	
	}
	
	@Test
	public void elJugadorDeberiaSubirLaEscalera() {
		Player a = new Player(); 
		Stair s = new Stair(100,600,10,85);
		int posInicial = a.getY();
		for(int x = 0; x < 85; x++) {
			a.goUp();
		}
		int posFinal = a.getY();
		assertTrue(posFinal < posInicial);		
	}
	
	/*
	@Test
	public void deberiaCrearNivel() {
		DonkeyPoob game = new DonkeyPoob();
		game.createLevel();
		assertTrue(game.getLenStairs() > 0);
		assertTrue(game.getLenFloors() > 0);
		assertTrue(game.getLenBarrels() > 0);
		assertTrue(game.getLenSurprises() > 0);
	}
	*/
	
	@Test
	public void elJugadorDeberiaAumentarSusPuntosAlTomarUnaSorpresaManzana() {
		Player a = new Player(); 
		Surprise manzana = new Apple(100,685);
		manzana.reaccione(a);
		assertTrue(a.getPoints() == 5);		
	}
	
	@Test
	public void elJugadorDeberiaAumentarSusPuntosAlTomarUnaSorpresaCereza() {
		Player a = new Player(); 
		Surprise manzana = new Cherry(100,685);
		manzana.reaccione(a);
		assertTrue(a.getPoints() == 10);		
	}
	
	@Test
	public void elJugadorDeberiaAumentarSuVidaAlTomarUnaSorpresaVida() {
		Player a = new Player(); 
		Surprise vida = new Life(100,685);
		vida.reaccione(a);
		assertTrue(a.getLives() == 4);		
	}
	
	@Test
	public void elJugadorDeberiaInvertirMovimientoAlTomarUnaSorpresaHongo() {
		//Deberia ser una prueba de aceptacion.
		Player a = new Player(); 
		Surprise hongo = new Fungus(100,685);
		hongo.reaccione(a);
		a.setX(500);
		for(int x = 0; x < 200; x++) {
			a.movePlayersRight(0);
		}
		int posFinal = a.getX();

		
		a.setX(500);
		for(int x = 0; x < 200; x++) {
			a.movePlayersLeft(0);
		}
		int posFinal2 = a.getX();
	}
	
	@Test
	public void elJugadorDeberiaDisminuirUnaVidaAlChocarConUnBarrilAmarillo() {
		Player a = new Player(); 
		Barrel b = new Barrel(100,685);
		int vidasIniciales = a.getLives();
		b.collisionPlayer(a);
		int vidasFinales = a.getLives();
		assert(vidasFinales == vidasIniciales - 1);
	}
	
	@Test
	public void elJugadorDeberiaDisminuirUnaVidaAlChocarConUnBarrilAzul() {
		Player a = new Player(); 
		Barrel b = new BlueBarrel(100,685);
		int vidasIniciales = a.getLives();
		b.collisionPlayer(a);
		int vidasFinales = a.getLives();
		assert(vidasFinales == vidasIniciales - 1);
	}
	
	@Test
	public void elJugadorDeberiaDisminuirUnaVidaAlChocarConUnBarrilRojo() {
		Player a = new Player(); 
		Barrel b = new RedBarrel(100,685);
		int vidasIniciales = a.getLives();
		b.collisionPlayer(a);
		int vidasFinales = a.getLives();
		assert(vidasFinales == vidasIniciales - 1);
	}
	
	@Test
	public void elJugadorDeberiaAumentarUnaVidaAlChocarConUnBarrilVerde() {
		Player a = new Player(); 
		Surprise c = new Hammer(100,685);
		c.reaccione(a);
		Barrel b = new GreenBarrel(100,685);
		int vidasIniciales = a.getLives();
		b.collisionPlayer(a);
		int vidasFinales = a.getLives();
		assert(vidasFinales == vidasIniciales + 1);
	}
	
	
	
	

	
	
	
	
	
	
	
	
}
