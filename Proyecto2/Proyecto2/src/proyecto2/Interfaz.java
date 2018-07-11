/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author tonyc
 */
public class Interfaz extends JFrame{
    	private JTextArea areaImpresion;
        private int cantAviones,turno=0,n=1,contador,cantEstaciones, cantEscritorios;
	private int pasajeros,desabordaje,mantenimiento,maletas,documentos, turnos;
	private String tamano;
        private ColaAvion colaAvion;
	private ListaMantenimiento listaMante;
        private ListaEscritorio listaEscri;
	private ListaAvion listaAvion;
	private ColaPasajero colaPasajero;
        private int contadorMaletas,numeroMaleta=1;
	private ListaEquipaje listaMaletas;
        private JPanel panel1, panel2;
        private JTextField numAviones,numEstaciones, numEscritorios;
        public static final String pequeño="Pequeño",mediano="Mediano",grande="Grande";
        public static final int salidaPasajero = 5;
	private JButton iniciar,turnoSiguiente, llegAvion, desbordaje, escritorios, mantenimient, equipaje;
        private JButton complete;
        private String ruta, ruta1, ruta2, ruta3, ruta4;
	
	public Interfaz() {
		super("Airport");
		initComponents();		
	}
        
	public void initComponents() {
		setLayout(null);
		setSize(1000,725);
		add(panel1());
		add(panel2());
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);		
	}
	
	public JPanel panel1() {
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 400, 650);
		panel1.setLayout(null);
		panel1.setVisible(true);		
		
		JLabel labelAviones = new JLabel("Numero de Aviones");
		labelAviones.setBounds(40, 70, 170, 20);
		labelAviones.setVisible(true);
		panel1.add(labelAviones);
		
		numAviones = new JTextField();
		numAviones.setBounds(210, 70, 40, 20);
		numAviones.setVisible(true);
		panel1.add(numAviones);		
                
                              
                JLabel labelEstaciones = new JLabel("Numero de Estaciones");
		labelEstaciones.setBounds(40, 120, 170, 20);
		labelEstaciones.setVisible(true);
		panel1.add(labelEstaciones);
		
		
		numEstaciones = new JTextField();
		numEstaciones.setBounds(210, 120, 40, 20);
		numEstaciones.setVisible(true);
		panel1.add(numEstaciones);
                
                JLabel labelEscritorios = new JLabel("Numero de Escritorios");
		labelEscritorios.setBounds(40, 170, 170, 20);
		labelEscritorios.setVisible(true);
		panel1.add(labelEscritorios);
                
                numEscritorios = new JTextField();
		numEscritorios.setBounds(210, 170, 40, 20);
		numEscritorios.setVisible(true);
		panel1.add(numEscritorios);
		
		iniciar = new JButton("Iniciar");
		iniciar.setBounds(100, 250, 85, 25);
		iniciar.setVisible(true);
		iniciar.addActionListener(new ActionListener(){

                 public void actionPerformed(ActionEvent ae){
                        turno=0;
			n=1;
			contador=0;
			numeroMaleta=1;
			areaImpresion.setText("");
			listaAvion = new ListaAvion();
			colaPasajero = new ColaPasajero();
			listaMaletas = new ListaEquipaje();
			listaMante = new ListaMantenimiento();
			colaAvion = new ColaAvion();
                        listaEscri = new ListaEscritorio();
                        iniciado = false;
			try {
				cantAviones = Integer.parseInt(numAviones.getText());
				cantEstaciones = Integer.parseInt(numEstaciones.getText());
                                cantEscritorios = Integer.parseInt(numEscritorios.getText());
			}
			catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ingrese numeros","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			anadirEstaciones();
                        anadirEscritorios();
                }
                });
		panel1.add(iniciar);
		
		turnoSiguiente = new JButton("Pasar Turno");
		turnoSiguiente.setBounds(90, 350, 105, 25);
		turnoSiguiente.setVisible(true);
		turnoSiguiente.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {						
                            listaAvion.bajaTurno();
                            listaMante.disminuirTurno();
                            listaEscri.bajarTurno(listaMaletas);
                            for (int i = 0; i < 5; i++) {
				listaAvion.eliminaAvion(colaAvion);
				listaMante.finalizaMantenimiento();
				colaAvion.pasarPorMantenimiento(listaMante);
                            }
                            if(!iniciado) {
                                anadirAviones();			
				turno++;						
				imprimir();
				iniciado = true;
                                graficar();
				return ;
                            }			
			sacarPasajeros(salidaPasajero);
                        listaEscri.addPasajeros();
			listaEscri.estaDisponible();
			anadirAviones();			
			turno++;						
			imprimir();
			comprobarLista();
                        graficar();
                    }
                });
		panel1.add(turnoSiguiente);
                
		llegAvion = new JButton("Aviones");
		llegAvion.setBounds(50, 500, 85, 25);
		llegAvion.setVisible(true);
		llegAvion.addActionListener(new ActionListener(){

                 public void actionPerformed(ActionEvent ae){
                    ruta = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\ListaDoble.png";
			ImageIcon icono = new ImageIcon(ruta);
			icono.getImage().flush();
			String titulo = "LLEGADA AVIONES";
			InterfazGrafica frameGraficas = new InterfazGrafica(icono,titulo);                 
                        frameGraficas.setVisible(true);
                 }});
                panel1.add(llegAvion);

                desbordaje = new JButton("Desbordaje");
		desbordaje.setBounds(200, 500, 100, 25);
		desbordaje.setVisible(true);
		desbordaje.addActionListener(new ActionListener(){

                 public void actionPerformed(ActionEvent ae){
                    ruta = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\PasajeroCola.png";
			ImageIcon icono = new ImageIcon(ruta);
			icono.getImage().flush();
			String titulo = "DESBORDAJE";
			InterfazGrafica frameGraficas = new InterfazGrafica(icono,titulo);                 
                        frameGraficas.setVisible(true);
                 }});
                panel1.add(desbordaje);
                
                escritorios = new JButton("Escritorios");
		escritorios.setBounds(50, 550, 100, 25);
		escritorios.setVisible(true);
		escritorios.addActionListener(new ActionListener(){

                 public void actionPerformed(ActionEvent ae){
                    ruta = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\EscritorioLista.png";
			ImageIcon icono = new ImageIcon(ruta);
			icono.getImage().flush();
			String titulo = "ESCRITORIOS";
			InterfazGrafica frameGraficas = new InterfazGrafica(icono,titulo);                 
                        frameGraficas.setVisible(true);
                 }});
                panel1.add(escritorios);

                mantenimient = new JButton("Mantenimiento");
		mantenimient.setBounds(200, 550, 150, 25);
		mantenimient.setVisible(true);
		mantenimient.addActionListener(new ActionListener(){

                 public void actionPerformed(ActionEvent ae){
                    ruta = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\MantenimientoLista.png";
			ImageIcon icono = new ImageIcon(ruta);
			icono.getImage().flush();
			String titulo = "MANTENIMIENTO";
			InterfazGrafica frameGraficas = new InterfazGrafica(icono,titulo);                 
                        frameGraficas.setVisible(true);
                 }});
                panel1.add(mantenimient);
                
                equipaje = new JButton("Equipaje");
		equipaje.setBounds(50, 600, 100, 25);
		equipaje.setVisible(true);
		equipaje.addActionListener(new ActionListener(){

                 public void actionPerformed(ActionEvent ae){
                    ruta = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\EquipajeLista.png";
			ImageIcon icono = new ImageIcon(ruta);
			icono.getImage().flush();
			String titulo = "EQUIPAJE";
			InterfazGrafica frameGraficas = new InterfazGrafica(icono,titulo);                 
                        frameGraficas.setVisible(true);
                 }});
                panel1.add(equipaje);
                
                complete = new JButton("Completo");
		complete.setBounds(200, 600, 100, 25);
		complete.setVisible(true);
		complete.addActionListener(new ActionListener(){

                 public void actionPerformed(ActionEvent ae){
                    ruta = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\ListaDoble.png";
			ImageIcon icono = new ImageIcon(ruta);
			icono.getImage().flush();
                    ruta1 = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\PasajeroCola.png";
			ImageIcon icono1 = new ImageIcon(ruta1);
			icono1.getImage().flush();
                    ruta2 = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\EscritorioLista.png";
			ImageIcon icono2 = new ImageIcon(ruta2);
			icono2.getImage().flush();
                    ruta3 = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\MantenimientoLista.png";
			ImageIcon icono3 = new ImageIcon(ruta3);
			icono3.getImage().flush();
                    ruta4 = "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\EquipajeLista.png";
			ImageIcon icono4 = new ImageIcon(ruta4);
			icono4.getImage().flush();
			InterfazGraficaCompleta frameGraficas = new InterfazGraficaCompleta(icono,icono1, icono2, icono3, icono4);                 
                        frameGraficas.setVisible(true);
                 }});
                panel1.add(complete);
                
		return panel1;
	}

	public JPanel panel2() {
		panel2 = new JPanel();	
		panel2.setBounds(400, 0, 600, 725);		
		panel2.setLayout(null);
		panel2.setVisible(true);		
                
		areaImpresion = new JTextArea();				
		areaImpresion.setBackground(Color.black);
		areaImpresion.setForeground(Color.blue);
		areaImpresion.setEditable(false);
				
		JScrollPane scroll = new JScrollPane(areaImpresion);
		scroll.setBounds(10, 50, 500, 625);
		scroll.setVisible(true);
		panel2.add(scroll);
		
		return panel2;
	}	
	boolean iniciado = false;
        
        public void anadirAviones() {		
		if(cantAviones>0) {			
			determinarTamano();
			listaAvion.setAvion(n,tamano, pasajeros, desabordaje, mantenimiento);
			n++;
			cantAviones--;
			anadirPasajeros();
			anadirMaletas();
		}									
	}
	
	public void anadirEstaciones() {
		while(cantEstaciones!=0) {
			listaMante.iniciarLista(cantEstaciones);
			cantEstaciones--;
		}
	}
	
	public void sacarPasajeros(int cantidad) {
		if(cantidad>0) {
			if(listaEscri.determinaEspacio()) {
				listaEscri.agregarPasajerosCola((Pasajero)colaPasajero.quitar());
			}
			else {
				listaMaletas.eliminarMaleta(colaPasajero.getMaletas());
				colaPasajero.eliminarPasajero();	
			}			
			sacarPasajeros(cantidad-1);
		}				
	}
	
	public void anadirPasajeros() {
		contador+=pasajeros;
		for (int i = (contador- pasajeros+1); i < (contador+1) ; i++) {
			determinarDoc();
			colaPasajero.setPasajero(i, maletas, documentos, turnos);
			contadorMaletas+=maletas;
		}		
	}
	
	public void anadirMaletas() {		
		while(contadorMaletas!=0) {
			listaMaletas.setMaleta(numeroMaleta);			
			numeroMaleta++;
			contadorMaletas--;
		}
	}
	
	public void determinarTamano() {
		int x = (int)(Math.random()*3+1);
		if(x==1) {
			tamano = "Pequeño";
			desabordaje = 1;
			pasajeros = (int) Math.floor(Math.random()*(10-5+1)+5);
			mantenimiento = (int)(Math.random()*3+1);
		}
		else if(x==2) {
			tamano = "Mediano";
			desabordaje = 2;
			pasajeros = (int) Math.floor(Math.random()*(25-15+1)+15);
			mantenimiento =  (int) Math.floor(Math.random()*(4-2+1)+2);
		}
		else if(x==3) {
			tamano ="Grande";
			desabordaje=3;
			pasajeros = (int) Math.floor(Math.random()*(40-30+1)+30);
			mantenimiento =  (int) Math.floor(Math.random()*(6-3+1)+3);
		}
	}

    public void anadirEscritorios() {			
		while(cantEscritorios!=0) {			
			listaEscri.iniciarLista();
			cantEscritorios--;
		}
	}
	
	
    public void determinarDoc() {
		maletas = (int)(Math.random()*4+1);
		documentos = (int)(Math.random()*10+1);	
                turnos = (int)(Math.random()*3+1);
    }
    public void comprobarLista() {
	if(listaAvion.estaVacia() && listaMaletas.estaVacia() && colaPasajero.estaVacia() && colaAvion.estaVacia()) {
            System.out.println("Esta vacio");
	}
    }
    	public void graficar() {
		Grafica g = new Grafica();
		g.crearDocumento(listaAvion.getPrimero(), "listaDoble.dot");
		g.generarImagen("listaDoble.dot", "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\ListaDoble.png");
		
		g.crearDocumentoDesbordaje(colaPasajero.getInicioNodo(), "colaPasajero.dot");
		g.generarImagen("colaPasajero.dot", "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\PasajeroCola.png");
		
		g.crearDocumentoMantenimiento(listaMante.getInicioNodo(), "listaMantenimiento.dot",colaAvion.getInicioNodo());
		g.generarImagen("listaMantenimiento.dot", "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\MantenimientoLista.png");
	
		g.crearDocumentoEscritorio(listaEscri.getInicioNodo(), "listaEscritorio.dot");
		g.generarImagen("listaEscritorio.dot", "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\EscritorioLista.png");
	
		g.crearDocumentoListaMaleta(listaMaletas.getInicioNodo(), listaMaletas.getInicioNodo(), "colaMaleta");
		g.generarImagen("colaMaleta.dot", "C:\\Users\\tonyc\\Desktop\\Proyecto2\\Proyecto2\\src\\imagenes\\EquipajeLista.png");
	}
	
    public void imprimir() {
	areaImpresion.append("\n+++++++++++++++++ TURNO " + turno + "++++++++++++++++\n");
	areaImpresion.append("----------- AVIONES -----------\n");
	listaAvion.imprimirAviones(areaImpresion);
	areaImpresion.append("----------- PASAJEROS -----------\n");
	colaPasajero.imprimirPasajero(areaImpresion);
	areaImpresion.append("---------MALETAS---------\n");
	listaMaletas.imprimirMaleta(areaImpresion);
	areaImpresion.append("\n---------ESTACIONES---------\n");
	listaMante.imprimirListaMantenimiento(areaImpresion);
	areaImpresion.append("\n---------COLA---------\n");
	colaAvion.imprimirColaAviones(areaImpresion);
        areaImpresion.append("\n-----------ESCRITORIOS---------\n");
        listaEscri.printListaEscritorio(areaImpresion);
	areaImpresion.append("\n+++++++++++++++++ FIN TURNO " + turno + "++++++++++++++++\n");
    }
}
