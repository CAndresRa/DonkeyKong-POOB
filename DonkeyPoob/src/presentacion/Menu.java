package presentacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import aplicacion.DonkeyPoob;
import aplicacion.DonkeyPoobExcepcion;
import persistencia.DonkeyPoobPersitencia;

public class Menu extends JFrame{
	private static final int height = 800;
	private static final int width = 900;
	private JMenuBar barraMenu;
	private JMenuItem open,save,saveAs,exitt;
	private JMenu file;
	private JFileChooser fileChooser;
	private JButton startGame;
	private JPanel principal, panelInferior, panelSeleccion ,modo, barriles, sorpresas, maquinas;
	private JLabel imagenInicial;
	private ButtonGroup modoGroup , maquinasGroup;
	private JRadioButton unJugador, dosJugadores;
	private JCheckBox barrilRojo, barrilAzul, barrilVerde, barrilAmarillo;
	private JCheckBox vida, cereza, manzana, hongo, hammer;
	private JRadioButton mimo, heroe, miedoso;
	private ArrayList<String> barrels, surprises;
	
	public Menu() {
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(width,height);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);
        fileChooser = new JFileChooser();
        fileChooser.setVisible(false);
		prepareMenu();
		preparePanelPrincipal();
		setResizable(false);	
	}
	
    private void prepareMenu(){
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        file = new JMenu("File");
        barraMenu.add(file);
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        exitt = new JMenuItem("exit");
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(exitt);
    }
    
	private void preparePanelPrincipal() {
		principal = new JPanel();
		principal.setLayout(null);
		principal.setSize(width, height);
		principal.setBackground(Color.BLACK);	
    	imagenInicial = new JLabel(new ImageIcon("resourses/inicio.png"));
    	imagenInicial.setBounds(0,0,900,400);
    	principal.add(imagenInicial);
    	startGame = new JButton(new ImageIcon("resourses/startGame.png"));
    	startGame.setOpaque(false);
    	startGame.setContentAreaFilled(false);
    	startGame.setBorderPainted(false);
    	panelInferior = new JPanel();
    	panelInferior.setLayout(new BorderLayout());
    	panelInferior.add(startGame, BorderLayout.CENTER);
    	panelInferior.setBounds(0,665,900,90);
    	panelInferior.setBackground(Color.BLACK);
    	principal.add(panelInferior);
    	prepareMenuSeleccion();
		add(principal);
	}
	
	private void prepareMenuSeleccion() {
		modo = new JPanel();
		modo.setLayout(new FlowLayout());
		modo.setBounds(0,400,900,60);
		modo.setBackground(Color.GRAY);
		barriles = new JPanel();
		barriles.setLayout(new FlowLayout());
		barriles.setBounds(0,460,900,60);
		barriles.setBackground(Color.GRAY);
		sorpresas = new JPanel();
		sorpresas.setLayout(new FlowLayout());
		sorpresas.setBounds(0,520,900,60);
		sorpresas.setBackground(Color.GRAY);
		maquinas = new JPanel();
		maquinas.setLayout(new FlowLayout());
		maquinas.setBounds(0,580,900,60);
		maquinas.setBackground(Color.GRAY);
		prepareBotones();
		principal.add(modo);
		principal.add(barriles);
		principal.add(sorpresas);
		principal.add(maquinas);
	}
	
	private void prepareBotones() {
    	modoGroup = new ButtonGroup();
    	unJugador = new JRadioButton("Un Jugador");
    	dosJugadores = new JRadioButton("Dos Jugadores");
    	modoGroup.add(unJugador);
    	modoGroup.add(dosJugadores);
    	modo.add(unJugador);
    	modo.add(dosJugadores);
    	
    	barrilRojo = new JCheckBox("Barril Rojo");
    	barrilAzul = new JCheckBox("Barril Azul");
    	barrilVerde = new JCheckBox("Barril Verde");
    	barrilAmarillo = new JCheckBox("Barril Amarillo");
    	
    	barriles.add(barrilRojo);
    	barriles.add(barrilAzul);
    	barriles.add(barrilAmarillo);
    	barriles.add(barrilVerde);
    	
    	vida = new JCheckBox("Vida");
    	cereza = new JCheckBox("Cereza");
    	manzana = new JCheckBox("Manzana");
    	hongo = new JCheckBox("Hongo");
    	hammer = new JCheckBox("Hammer");
    	
    	sorpresas.add(vida);
    	sorpresas.add(cereza);
    	sorpresas.add(manzana);
    	sorpresas.add(hongo);
    	sorpresas.add(hammer);
    	
    	mimo = new JRadioButton("Mimo");
    	heroe = new JRadioButton("Heroe");
    	miedoso = new JRadioButton("Miedoso");
    	
    	maquinasGroup = new ButtonGroup();
    	maquinasGroup.add(mimo);
    	maquinasGroup.add(heroe);
    	maquinasGroup.add(miedoso);
    	maquinas.add(mimo);
    	maquinas.add(heroe);
    	maquinas.add(miedoso);
    	
    	
		
	}
	
	private void prepareAcciones() {
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent ev){
	                exit();
	            }
	        });

	        exitt.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent ev){
					exit();
	            }
	        });

	        open.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent ev){
					accionOpenFile();
	            }
	        });

	        save.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent ev){
	                accionSave();
	            }
	        });        

	        saveAs.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent ev){
	                accionSalveAs();
	            }
	        });
	        
	        startGame.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent ev){
	                accionStartGame();
	            }
	        });
	}
	
	
	private void exit(){
	    if (JOptionPane.showConfirmDialog(null, "Desea salir?", "WARNING",
        	JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        	System.exit(0);
	    } 
    }
	
    /**
     * Permite realizar accion abrir
     */
	private void accionOpenFile(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Abrir");
		int seleccion = fileChooser.showOpenDialog(open);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				DonkeyPoob game = DonkeyPoobPersitencia.open(fileChooser.getSelectedFile());

			}
		}
		catch(DonkeyPoobExcepcion e){

			JOptionPane.showMessageDialog(fileChooser,e.getMessage());
		}
	}

	
	/**
	 * Permite realizar la accion Salvar 
	 */
    private void accionSave(){
		fileChooser.setVisible(true);
		int seleccion = fileChooser.showOpenDialog(save);
		if (seleccion == JFileChooser.APPROVE_OPTION){
			File fichero = fileChooser.getSelectedFile();
			JOptionPane.showMessageDialog(save,"Archivo Guardado");
		}
    }
    
    /**
     * Permite realizar la accion Guardar como 
     */
    private void accionSalveAs(){
        fileChooser.setVisible(true);
        int seleccion = fileChooser.showSaveDialog(null);;
        if(seleccion == JFileChooser.APPROVE_OPTION){
            File fichero = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(saveAs,"Archivo Guardado");
        }
    }
    
    private ArrayList<String> completarSopresas() {
    	ArrayList<String> completaSorpresa = new ArrayList<>();
    	completaSorpresa.add("vida");
    	completaSorpresa.add("manzana");
    	completaSorpresa.add("cereza");
    	completaSorpresa.add("hongo");
    	completaSorpresa.add("hammer");
    	return completaSorpresa;
    }
    private ArrayList<String> completarBarriles() {
    	ArrayList<String> completaBarriles = new ArrayList<>();
    	completaBarriles.add("amarillo");
    	completaBarriles.add("azul");
    	completaBarriles.add("rojo");
    	completaBarriles.add("verde");
    	return completaBarriles;
    }
	
    private void accionStartGame() { 
    	surprises = new ArrayList<>();
    	barrels = new ArrayList<>();
    	//Barriles posibles 
    	if(barrilAmarillo.isSelected()) {barrels.add("amarillo");};
    	if(barrilAzul.isSelected()) {barrels.add("azul");};
    	if(barrilRojo.isSelected()) {barrels.add("rojo");};
    	if(barrilVerde.isSelected()) {barrels.add("verde");};
    	//sorpresas seleccionadas 
    	if(vida.isSelected()) {surprises.add("vida");};
    	if(hongo.isSelected()) {surprises.add("hongo");};
    	if(cereza.isSelected()) {surprises.add("cereza");};
    	if(manzana.isSelected()) {surprises.add("manzana");};
    	if(hammer.isSelected()) {surprises.add("hammer");};
    	//modo seleccionado 
    	int modoSeleccionado = 0;
    	if(unJugador.isSelected()) {modoSeleccionado = 1;};
    	if(dosJugadores.isSelected()) {modoSeleccionado = 2;};
    	//cpu seleccionado
    	String cpuPlayer = "";
    	if(mimo.isSelected()) {cpuPlayer = "mimo";};
    	if(heroe.isSelected()) {cpuPlayer = "heroe";};
    	if(miedoso.isSelected()) {cpuPlayer = "miedoso";};
    	this.setVisible(false);
    	if(modoSeleccionado == 0) {modoSeleccionado = 1;};
    	try {
		DonkeyPoobGUI donkeyPoob = new DonkeyPoobGUI(modoSeleccionado, barrels, surprises, cpuPlayer);
		donkeyPoob.setVisible(true);
    	}
    	catch(IndexOutOfBoundsException e) {
    		DonkeyPoobPersitencia.registre(e);
    		this.setVisible(true);
    	}
    }    

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.setVisible(true);	
	} 
}
