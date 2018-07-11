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
public class ListaAvion extends ListaDoble{
    
	
	public ListaAvion() {
		super();
	}	
	
	public void setAvion(int numero, String tipo, int pasajeros, int turnosDesabordaje, int turnosMantenimiento) {
		Avion nuevo = new Avion(numero,tipo,pasajeros,turnosDesabordaje,turnosMantenimiento);		
		insertarInicio(nuevo);
	}
	
	public void setAvion(Avion nuevo) {
		insertarFinal(nuevo);
	}
	
	public Avion getAvion(int index) {
		Avion avion = (Avion)obtenerComponente(index);
		return avion;
	}
        
        public void eliminaAvion(ColaAvion colaAvion) {
            for(int i = 0; i<getTamano(); i++) {
		Avion avion = (Avion)obtenerComponente(i);
		if(avion.getTurnosDesabordaje()<=0) {
                    eliminarComponente(i);
                    colaAvion.setAvion(avion);
		}
            }
	}
	
	public void bajaTurno() {
		for(int i=0 ; i<getTamano();i++) {
			Avion avion = (Avion)obtenerComponente(i);
			avion.setTurnosDesabordaje(avion.getTurnosDesabordaje()-1);
		}
	}
        
        public Nodo getPrimero() {
            return ini;
	}
	
	public void imprimirAviones(JTextArea txt) {
		for(int i=0 ; i<getTamano(); i++) {
			Avion avion = (Avion)obtenerComponente(i);
			txt.append("\nAVION: " + avion.getNumero() + "\n");
			txt.append("	TIPO: " + avion.getTipo() + "\n 	PASAJEROS: " + avion.getPasajeros()  + "\n 	TURNOS DESABORDAJE: " + avion.getTurnosDesabordaje() + "\n 	TURNOS MANTENIMIENTO " + avion.getTurnosMantenimiento() + "\n");		
		}
	}
}
