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
public class ListaMantenimiento extends ListaSimple{
    private boolean est;
	
	public ListaMantenimiento() {
		super();
	}
	
	public void setMantenimiento(int numero, boolean estado, Avion avion) {
		Mantenimiento mantenimiento = new Mantenimiento(numero,estado,avion);
	}
	
	public void addAviones(Avion avion) {
		for(int i=0 ; i<getTamano();i++) {
			est=false;
			Mantenimiento mantenimiento = (Mantenimiento)getComponente(i);
			if(mantenimiento.getEstatus()) {
				mantenimiento.setEstatus(false);
				mantenimiento.setAvion(avion);
				est=true;
				return;
			}
			else {
				est=false;
			}
		}
	}	
	
	public void iniciarLista(int numero) {
		Mantenimiento nuevo = new Mantenimiento(numero,true,null);
		insertarInicio(nuevo);
	}
	
	public void disminuirTurno() {
		for(int i=0 ; i<getTamano();i++) {
			Mantenimiento m = (Mantenimiento)getComponente(i);
			if(m.getAvion()!=null)
			m.getAvion().setTurnosMantenimiento(m.getAvion().getTurnosMantenimiento()-1);
		}
	}
	
	public void finalizaMantenimiento() {
		for(int i=0 ; i<getTamano();i++) {
			Mantenimiento m = (Mantenimiento)getComponente(i);
			if(m.getAvion()!=null) {
				if(m.getAvion().getTurnosMantenimiento()<=0) {
					m.setAvion(null);
					m.setEstatus(true);
				}
			}
		}
	}
	
	public boolean getEstado() {
		return est;
	}
			
	public void imprimirListaMantenimiento(JTextArea txt) {
		for(int i=0 ; i<getTamano();i++) {
			Mantenimiento m = (Mantenimiento)getComponente(i);
			if(m.getAvion()==null) {
				txt.append("\nESTACION " + (i+1));
				txt.append("\n	ESTADO: " + m.getDisponible() + "\n");
			}
			else {
				txt.append("\nESTACION " + (i+1));
				txt.append("\n	ESTADO: " + m.getDisponible() + m.getAvion());
			}
		}
	}
}
