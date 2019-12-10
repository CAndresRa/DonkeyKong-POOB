package presentacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import aplicacion.DonkeyPoob;
import aplicacion.DonkeyPoobExcepcion;
import persistencia.DonkeyPoobPersitencia;

public class DonkeyPoobGUI extends JFrame{
	private static final int height = 800;
	private static final int width = 1000;
	private JMenuBar barraMenu;
	private JMenuItem open,save,importar,export, startGame, pausarGame;
	private JMenu file;
	private Painter showGame;
	private JPanel principal, puntajes;
	private JFileChooser fileChooser;
	private JLabel p1, p2, cpuP, vidas;
	private JLabel punP1, punP2, punP3, nVidas;
	JPanel contenedor ;
	private int modo;
	private ArrayList<String> barrilesSelect, sorpresasSelect;
	private String cpuSelect;

	/**
	 * Contructor clase DonkeyPoobGUI
	 */
	public DonkeyPoobGUI(int modo, ArrayList<String> barrilesSelect,  ArrayList<String> sorpresasSelect, String cpuSelect) {
		this.modo = modo;
		this.barrilesSelect = barrilesSelect;
		this.sorpresasSelect = sorpresasSelect;
		this.cpuSelect = cpuSelect;
		preparePanelPrincipal();
		prepareButton();
		prepareLabels();
		prepareElementos();
		prepareAcciones();
		prepareJuego();
	}

	/**
	 * prepara elementos
	 */
	private void prepareElementos() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(width,height);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		setResizable(false);
		fileChooser = new JFileChooser();
		fileChooser.setVisible(false);
	}
	
	/**
	 * prepara panel principal
	 */
	private void preparePanelPrincipal() {
		principal = new JPanel();
		principal.setLayout(new BorderLayout());
		principal.setSize(width, height);
		principal.setBackground(Color.BLACK);
		add(principal);
	}
	
	/*
	 * Prepara el juego 
	 */
	private void prepareJuego(){
		showGame = new Painter(this,modo,barrilesSelect,sorpresasSelect,cpuSelect);
		principal.add(showGame,BorderLayout.CENTER);
	}
	
	/*
	 * Prapra boton para iniciar
	 */
	private void prepareButton() {
		
		barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        file = new JMenu("File");
        barraMenu.add(file);
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        importar = new JMenuItem("importar");
        export = new JMenuItem("export");
        startGame = new JMenuItem("Iniciar Juego");
        pausarGame = new JMenuItem("Pausar Juego");
        
        file.add(open);
        file.add(save);
        file.add(importar);
        file.add(export);
        file.add(startGame);
        file.add(pausarGame);
        
		principal.add(barraMenu);
	}
	
	/*
	 * Prepara mensajes
	 */
	private void prepareLabels() {
		puntajes = new JPanel();
		puntajes.setLayout(new FlowLayout());
		puntajes.setSize(1000,100);
		principal.add(puntajes, BorderLayout.NORTH);
		p1 = new JLabel("Puntos del Jugador = ");
		punP1 = new JLabel("0");
		vidas = new JLabel("Vidas del jugador = ");
		nVidas = new JLabel("3");
		puntajes.add(p1);
		puntajes.add(punP1);
		puntajes.add(vidas);
		puntajes.add(nVidas);	
	}
	
	/*
	 * Prepara mensaejs
	 */
	private void prepareAcciones() {
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent ev){
                exit();
            }
        });
        
        startGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				try {
					accionPlay();
				} catch (DonkeyPoobExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        pausarGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev){
        		accionStop();
        	}
		});
        
		save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionSaveFile();
            }
		});
		
		open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionOpenFile();
            }
		});
	}
	
	private void accionSaveFile(){
		accionStop();
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Guardar");
    	fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo DAT","dat"));
		int seleccion = fileChooser.showSaveDialog(save);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				showGame.salvar(fileChooser.getSelectedFile());
			}
			else{
				accionPlay();
			}
		}
		catch(DonkeyPoobExcepcion e){
			DonkeyPoobPersitencia.registre(e);
			JOptionPane.showMessageDialog(save,e.getMessage());
		}
	}
	
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
			DonkeyPoobPersitencia.registre(e);
			JOptionPane.showMessageDialog(fileChooser,e.getMessage());
		}
	}
	
	/**
	 * @param vidas para mostrar
	 * @param puntaje para mostrar
	 */
	public void actualizaResultados(int vidas, int puntaje) {
		punP1.setText(Integer.toString(puntaje));
		nVidas.setText(Integer.toString(vidas));
	}
	
	
	/**
	 * cierra la ventana
	 */
    private void exit(){
	    if (JOptionPane.showConfirmDialog(null, "Desea salir?", "WARNING",
        	JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        	System.exit(0);

	    } 
    }

    /**
     * Inicia el juego 
     * @throws DonkeyPoobExcepcion 
     */
    private void accionPlay() throws DonkeyPoobExcepcion {
    	showGame.accionJugar();
    }
    
    private void accionStop() {
    	showGame.stopTimers();
    }
	
	


}
