/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listairport;

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
        private int cantAviones,turno=0,n=1,contador,cantEstaciones;
	private int pasajeros,desabordaje,mantenimiento,maletas,documentos;
	private String tamano;
        private ColaAvion colaAvion;
	private ListaMantenimiento listaMante;
	private ListaAvion listaAvion;
	private ColaPasajero colaPasajero;
        private int contadorMaletas,numeroMaleta=1;
	private ListaMaletas listaMaletas;
        private JPanel panel1, panel2;
        private JTextField numAviones,numEstaciones;
	private JButton iniciar,turnoSiguiente;

	
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
		labelEstaciones.setBounds(40, 150, 170, 20);
		labelEstaciones.setVisible(true);
		panel1.add(labelEstaciones);
		
		
		numEstaciones = new JTextField();
		numEstaciones.setBounds(210, 150, 40, 20);
		numEstaciones.setVisible(true);
		panel1.add(numEstaciones);
		
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
			listaMaletas = new ListaMaletas();
			listaMante = new ListaMantenimiento();
			colaAvion = new ColaAvion();
			try {
				cantAviones = Integer.parseInt(numAviones.getText());
				cantEstaciones = Integer.parseInt(numEstaciones.getText());
			}
			catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ingrese numeros","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			anadirEstaciones();
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
				return ;
                            }			
			sacarPasajeros();			
			anadirAviones();			
			turno++;						
			imprimir();
			comprobarLista();
                    }
                });
		panel1.add(turnoSiguiente);
		
		return panel1;
	}

	public JPanel panel2() {
		panel2 = new JPanel();	
		panel2.setBounds(400, 0, 600, 725);		
		panel2.setLayout(null);
		panel2.setVisible(true);		
                
		areaImpresion = new JTextArea();				
		areaImpresion.setBackground(Color.black);
		areaImpresion.setForeground(Color.white);
		areaImpresion.setEditable(false);
				
		JScrollPane scroll = new JScrollPane(areaImpresion);
		scroll.setBounds(10, 50, 500, 625);//380  480
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
			listaMante.iniciarLista();
			cantEstaciones--;
		}
	}
	
	public void sacarPasajeros() {
		int x =5;
		while(x!=0) {					
			listaMaletas.eliminarMaleta(colaPasajero.getMaletas());
			colaPasajero.eliminarPasajero();			
			x--;
		}				
	}
	
	public void anadirPasajeros() {
		contador+=pasajeros;
		for (int i = (contador- pasajeros+1); i < (contador+1) ; i++) {
			determinarDoc();
			colaPasajero.setPasajero(i, maletas, documentos);
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
	
	
    public void determinarDoc() {
		maletas = (int)(Math.random()*4+1);
		documentos = (int)(Math.random()*10+1);		
    }
    public void comprobarLista() {
	if(listaAvion.estaVacia() && listaMaletas.estaVacia() && colaPasajero.estaVacia() && colaAvion.estaVacia()) {
            System.out.println("Esta vacio");
	}
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
	areaImpresion.append("\n+++++++++++++++++ FIN TURNO " + turno + "++++++++++++++++\n");
    }
}
