/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author tonyc
 */
public class Escritorio {
    private boolean estado;
	private Pasajero pasajero;
	private char nombre;
	private ColaPasajero colaPasajero;	
	private Pila pila;
	
	public Escritorio(boolean estado, Pasajero pasajero,char nombre, ColaPasajero colaPasajero, Pila pila) {
		this.estado=estado;
		this.pasajero=pasajero;
		this.nombre=nombre;
		this.colaPasajero=colaPasajero;		
		this.pila=pila;
	}

	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Pila getPila() {
		return pila;
	}
	
	public void setPila(Pila pila) {
		this.pila=pila;
	}
	
	public ColaPasajero getColaPasajero() {
		return colaPasajero;
	}
	
	public void setColaPasajero(ColaPasajero colaPasajero) {
		this.colaPasajero=colaPasajero;
	}
	
	public char getNombre() {
		return nombre;
	}
	
	public void setNombre(char nombre) {
		this.nombre=nombre;
	}
	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}
	
	public String getDisponible() {
		if(getEstado()) {
			return "Disponible";
		}
		else {
			return "Ocupado";
		}
	}
}
