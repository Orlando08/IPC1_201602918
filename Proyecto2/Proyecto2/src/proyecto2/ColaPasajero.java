/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import javax.swing.JTextArea;

/**
 *
 * @author tonyc
 */
public class ColaPasajero extends Cola{
    
    public ColaPasajero() {
	super();
    }
		
    public int getMaletas() {
	if(tamanoCola()!=0) {
            Pasajero p = (Pasajero)obtenerComponente(tamanoCola()-1);
            return p.getMaletas();
	}
        return 0;
    }
        
    public void setPasajero(int numero,int maletas, int documentos, int turnos) {
	Pasajero pasajero = new Pasajero(numero,maletas,documentos,turnos);
	insertar(pasajero);
    }
	
    public void setPasajero(Pasajero nuevo) {
	insertar(nuevo);
    }	
	
    public void eliminarPasajero() {
	quitar();
    }

	
    public void imprimirPasajero(JTextArea txt) {
	for (int i = tamanoCola()-1; i >= 0; i--) {
            Pasajero p = (Pasajero)obtenerComponente(i);
            txt.append("\nPASAJERO: " + p.getNumero() +  "\n");
            txt.append("	MALETAS: " + p.getMaletas() + "\n 	DOCUMENTOS: " + p.getDocumentos() + "\n"+ "\n 	TURNOS REGISTRO: " + p.getTurnos() + "\n");
	}
    }
}
