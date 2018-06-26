/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listairport;

import javax.swing.JTextArea;

/**
 *
 * @author tonyc
 */
public class ColaAvion extends Cola{
    public ColaAvion() {
		super();
	}
	
	public void setAvion(int num, String tipo, int pasajeros, int turnosDesabordaje, int turnosMantenimiento) {
		Avion nuevo = new Avion(num,tipo,pasajeros,turnosDesabordaje,turnosMantenimiento);		
		insertar(nuevo);
	}
	
	public void setAvion(Avion nuevo) {		
			insertar(nuevo);									
	}	
	
	public void pasarPorMantenimiento(ListaMantenimiento lMantenimiento) {
		if(inicioCola()==null) 
			return ;
		
		lMantenimiento.addAviones((Avion) inicioCola());
		if(lMantenimiento.getEstado()) {
			saleAvion();
		}		
	}
	
	public void saleAvion() {
		quitar();
	}
	
	public void imprimirColaAviones(JTextArea txt) {
		for (int i = tamanoCola()-1; i >= 0; i--) {
			Avion a = (Avion)obtenerComponente(i);			
			txt.append("	AVION: " + a.getNumero() + "\n	TIPO: " + a.getTipo() + "\n 	PASAJEROS: " + a.getPasajeros()  + "\n 	TURNOS DESABORDAJE: " + a.getTurnosDesabordaje() + "\n 	TURNOS MANTENIMIENTO " + a.getTurnosMantenimiento() + "\n");
		}
	}
}
